package com.ta.c02_5.controller;

import com.ta.c02_5.model.ProduksiModel;
import com.ta.c02_5.service.ProduksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProduksiController {
    @Autowired
    private ProduksiService produksiService;

    @GetMapping("/produksi/viewall")
    public String listProduksi(Model model) {
        List<ProduksiModel> listProduksi = produksiService.getProduksiList();
        model.addAttribute ( "listProduksi",listProduksi);
        return "viewall-produksi" ;
    }
}
