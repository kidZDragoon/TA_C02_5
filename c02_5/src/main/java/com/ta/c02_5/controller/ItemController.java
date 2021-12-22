package com.ta.c02_5.controller;


import com.ta.c02_5.model.ItemModel;
import com.ta.c02_5.rest.ItemDetail;
import com.ta.c02_5.restcontroller.ItemRestController;
import com.ta.c02_5.service.ItemRestService;

import com.ta.c02_5.model.MesinModel;
import com.ta.c02_5.model.PegawaiModel;
import com.ta.c02_5.model.ProduksiModel;
import com.ta.c02_5.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;


@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemRestService itemRestService;

    @Autowired
    private ItemRestController itemRestController;

    @Autowired
    private MesinService mesinService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PegawaiService pegawaiService;

    @Autowired
    private ProduksiService produksiService;


    @GetMapping("/viewall")
    public String getListItem(Model model){
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("role", role);
        model.addAttribute("listItem", itemRestService.getListItem());
        return "viewall-item";
    }

    @GetMapping("/view/{uuid}")
    public String getItemByUUID(Model model, @PathVariable String uuid){
        model.addAttribute("produksiList", produksiService.getListProduksiByIdItem(uuid));
        model.addAttribute("item", itemRestService.getItemByUUID(uuid));

        return "view-byUUID";
    }


    @GetMapping("/propose")
    public String proposeItemForm(Model model) {
        HashMap<String, List<ItemDetail>> allItemHashMap = itemRestService.getListItem();
        model.addAttribute("item", new ItemModel());
        model.addAttribute("listItem", allItemHashMap.keySet());
        return "form-propose-item";
    }

    @PostMapping("/propose")
    public String proposeItemSubmit(
            @ModelAttribute ItemModel item,
            Model model
    ) {
        model.addAttribute("item", item);
        return "submit-propose-item";
    }

    @GetMapping("/update/{uuid}")
    public String updateItemForm(
            @PathVariable String uuid,
            Model model
    ){
        ItemDetail item = itemRestService.getItemByUUID(uuid);
        int assign = itemService.assignKategori(item);
        mesinService.getListMesinByIdKategori(assign);

        model.addAttribute("item", itemRestService.getItemByUUID(uuid));
        model.addAttribute("listKategori", mesinService.getListMesinByIdKategori(assign));

        return "update-stok-item";
    }

    @PostMapping("/update")
    public String updateStokItemSubmit(
            @ModelAttribute ProduksiModel produksi,
            Integer tambahan_stok, String uuid, HttpServletRequest request, Integer idMesin,
            Model model
    ) {
        ItemDetail itemUUID = itemRestService.getItemByUUID(uuid);
        int idkategori = itemService.assignKategori(itemUUID);

        Mono<HashMap> update = itemRestService.updateStokItem(itemUUID);
        String status = update.block().get("status").toString();

        if(status.equals("200")){


            Date date =new java.util.Date();
            produksi.setIdItem(uuid);
            produksi.setTanggalProduksi(date);
            produksi.setIdKategori(idkategori);
            produksi.setTambahanStok(tambahan_stok);

            //Menambah Stok
            itemUUID.setStok(itemUUID.getStok() + tambahan_stok);

            //menambahkan counter pada pegawai
            Principal principal = request.getUserPrincipal();
            PegawaiModel pegawai = pegawaiService.findByUsername(principal.getName());
            pegawai.setCounter(pegawai.getCounter() + 1);

            //set id_pegawai
            produksi.setPegawai(pegawai);
            //Mengurangi kapasitas Mesin
            MesinModel mesin = mesinService.findByIdMesin(idMesin);
            mesin.setKapasitas(mesin.getKapasitas() - 1);
    //        mesin.setIdMesin(idMesin);

            //set id-mesin
            produksi.setMesin(mesin);

            itemService.updateItem(produksi);
            itemRestService.updateStokItem(itemUUID);
            return "update-stok";
        }else{
           return "gagalUpdate";
        }

    }
}
