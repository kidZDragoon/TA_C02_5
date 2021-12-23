package com.ta.c02_5.controller;

import com.ta.c02_5.model.*;
import com.ta.c02_5.rest.ItemDetail;
import com.ta.c02_5.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/request-update-item")
public class RequestUpdateItemController {
    @Autowired private DeliveryService deliveryService;
    @Autowired private ItemRestService itemRestService;
    @Autowired private ItemService itemService;
    @Autowired private MesinService mesinService;
    @Autowired private PegawaiService pegawaiService;
    @Autowired private ProduksiService produksiService;
    @Autowired private RequestUpdateItemService requestUpdateItemService;
    @Autowired private RoleService roleService;

    @RequestMapping("/viewall")
    public String listRequestUpdateItem(Model model) {
        List<RequestUpdateItemModel> listRequestUpdateItem = requestUpdateItemService.getRequestUpdateItemList();
        model.addAttribute("listRequestUpdateItem", listRequestUpdateItem);
        model.addAttribute("params", "request-update-item");

        return "viewall-request-update-item";
    }

    @GetMapping("/update/{idRequestUpdateItem}")
    public String updateRequestUpdateItem(@PathVariable Integer idRequestUpdateItem,
                                          Model model)
    {
        RequestUpdateItemModel requestUpdateItem = requestUpdateItemService.getRequestUpdateItemByIdRequestUpdateItem(idRequestUpdateItem);
        ItemDetail itemDetail = itemRestService.getItemByUUID(requestUpdateItem.getIdItem());
        List<MesinModel> listMesin = mesinService.getListMesinByIdKategori(itemService.assignKategori(itemDetail));

        model.addAttribute("item", itemDetail);
        model.addAttribute("requestUpdateItem", requestUpdateItem);
        model.addAttribute("listMesin", listMesin);

        return "update-stok-item-request-update-item";
    }

    @PostMapping("/update")
    public String updateRequestUpdateItemSubmit(
            @ModelAttribute ProduksiModel produksi,
            String uuid,
            HttpServletRequest request,
            Integer idMesin,
            Integer idRequestUpdateItem
    ) {
        RequestUpdateItemModel requestUpdateItem = requestUpdateItemService.getRequestUpdateItemByIdRequestUpdateItem(idRequestUpdateItem);
        ItemDetail itemDetail = itemRestService.getItemByUUID(uuid);

        itemDetail.setStok(itemDetail.getStok() + requestUpdateItem.getTambahanStok());

        Mono<HashMap> update = itemRestService.updateStokItem(itemDetail);
        String status = update.block().get("status").toString();

        if(status.equals("200")){
            Principal principal = request.getUserPrincipal();
            PegawaiModel pegawai = pegawaiService.findByUsername(principal.getName());

            MesinModel mesin = mesinService.findByIdMesin(idMesin);

            produksi.setIdItem(uuid);
            produksi.setIdKategori(requestUpdateItem.getIdKategori());
            produksi.setIdCabang(requestUpdateItem.getIdCabang());
            produksi.setTambahanStok(requestUpdateItem.getTambahanStok());
            produksi.setTanggalProduksi(new java.util.Date());
            produksi.setPegawai(pegawai);
            produksi.setRequestUpdateItem(requestUpdateItem);
            produksi.setMesin(mesin);

            produksiService.addProduksi(produksi);

            mesin.setKapasitas(mesin.getKapasitas() - 1);
            mesinService.updateMesin(mesin);

            requestUpdateItem.setExecuted(true);
            requestUpdateItemService.updateRequestItemModel(requestUpdateItem);

            pegawai.setCounter(pegawai.getCounter() + 1);
            pegawaiService.updatePegawai(pegawai);

            return "update-stok";
        }else{
            return "gagalUpdate";
        }

    }

    @GetMapping("/assign-kurir/{idRequestUpdateItem}")
    public String assignKurir(
            @PathVariable Integer idRequestUpdateItem,
            Model model
    ) {
        RequestUpdateItemModel requestUpdateItem = requestUpdateItemService.getRequestUpdateItemByIdRequestUpdateItem(idRequestUpdateItem);

        List<RoleModel> listRole = roleService.getListRole();

        RoleModel kurirRole = new RoleModel();
        for (RoleModel role : listRole) {
            if (role.getNamaRole().equalsIgnoreCase("STAFF_KURIR")) {
                kurirRole = role;
                break;
            }
        }

        List<PegawaiModel> listKurir = pegawaiService.getListUserByRole(kurirRole);

        DeliveryModel delivery = new DeliveryModel();

        model.addAttribute("delivery", delivery);
        model.addAttribute("listKurir", listKurir);
        model.addAttribute("requestUpdateItem", requestUpdateItem);


        return "form-assign-kurir";
    }

    @PostMapping("/assign-kurir")
    public String assignKurirSubmit(
            @ModelAttribute DeliveryModel delivery,
            HttpServletRequest request,
            String usernameKurir,
            Integer idRequestUpdateItem
    ) {
        RequestUpdateItemModel requestUpdateItem = requestUpdateItemService.getRequestUpdateItemByIdRequestUpdateItem(idRequestUpdateItem);

        PegawaiModel kurir = pegawaiService.findByUsername(usernameKurir);

        delivery.setIdKurir(kurir.getIdPegawai());
        delivery.setIdCabang(requestUpdateItem.getIdCabang());
        delivery.setTanggalDibuat(Calendar.getInstance().getTime());
        delivery.setTanggalDikirim(null);
        delivery.setSent(false);
        delivery.setRequestUpdateItem(requestUpdateItem);
        delivery.setPegawai(kurir);

        deliveryService.addDelivery(delivery);

        requestUpdateItem.setIdDelivery(delivery.getIdDelivery());
        requestUpdateItemService.updateRequestItemModel(requestUpdateItem);

        Principal principal = request.getUserPrincipal();
        PegawaiModel pegawai = pegawaiService.findByUsername(principal.getName());
        pegawai.setCounter(pegawai.getCounter() + 1);
        pegawaiService.updatePegawai(pegawai);

        return "assign-kurir";
    }
}