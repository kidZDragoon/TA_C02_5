package com.ta.c02_5.controller;

import com.ta.c02_5.model.MesinModel;
import com.ta.c02_5.model.PegawaiModel;
import com.ta.c02_5.model.ProduksiModel;
import com.ta.c02_5.model.RequestUpdateItemModel;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class RequestUpdateItemController {

    @Qualifier("requestUpdateItemServiceImpl")
    @Autowired
    private RequestUpdateItemService requestUpdateItemService;

    @Autowired
    private ItemRestService itemRestService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private MesinService mesinService;

    @Autowired
    private PegawaiService pegawaiService;

    @RequestMapping("/request-update-item/viewall")
    public String listRequestUpdateItem(Model model) {
        List<RequestUpdateItemModel> listRequestUpdateItem = requestUpdateItemService.getRequestUpdateItemList();
        model.addAttribute("listRequestUpdateItem", listRequestUpdateItem);
        model.addAttribute("params", "request-update-item");

        return "viewall-request-update-item";
    }

    @GetMapping("/request-update-item/update/{idRequestUpdateItem}")
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

    @PostMapping("/request-update-item/update")
    public String updateRequestUpdateItemSubmit(
            @ModelAttribute ProduksiModel produksi,
            Integer tambahan_stok,
            String uuid,
            HttpServletRequest request,
            Integer idMesin,
            Integer idRequestUpdateItem
    ) {
        RequestUpdateItemModel requestUpdateItem = requestUpdateItemService.getRequestUpdateItemByIdRequestUpdateItem(idRequestUpdateItem);
        ItemDetail itemDetail = itemRestService.getItemByUUID(uuid);
        int idKategori = itemService.assignKategori(itemDetail);

        System.out.println(itemDetail.getStok());
        System.out.println(tambahan_stok);
        // Menambahkan stok
        itemDetail.setStok(itemDetail.getStok() + tambahan_stok);

        System.out.println(itemDetail.getStok());

        Mono<HashMap> update = itemRestService.updateStokItem(itemDetail);
        String status = update.block().get("status").toString();

        if(status.equals("200")){
            Date date = new java.util.Date();
            produksi.setIdItem(uuid);
            produksi.setTanggalProduksi(date);
            produksi.setIdKategori(idKategori);
            produksi.setTambahanStok(tambahan_stok);

            // Menambahkan counter pada pegawai
            Principal principal = request.getUserPrincipal();
            PegawaiModel pegawai = pegawaiService.findByUsername(principal.getName());
            pegawai.setCounter(pegawai.getCounter() + 1);

            // Set ID Pegawai
            produksi.setPegawai(pegawai);

            // Mengurangi kapasitas mesin
            MesinModel mesin = mesinService.findByIdMesin(idMesin);
            mesin.setKapasitas(mesin.getKapasitas() - 1);

            // Set ID Mesin
            produksi.setMesin(mesin);

            // Bound ProduksiModel to RequestUpdateItemModel
            produksi.setRequestUpdateItem(requestUpdateItem);

            // Save ProduksiModel to database
            itemService.updateItem(produksi);

            // Change Executed State
            System.out.println(requestUpdateItem.isExecuted());
            requestUpdateItem.setExecuted(true);
            System.out.println(requestUpdateItem.isExecuted());
            requestUpdateItemService.updateRequestItemModel(requestUpdateItem);

            return "update-stok";
        }else{
            return "gagalUpdate";
        }

    }
}