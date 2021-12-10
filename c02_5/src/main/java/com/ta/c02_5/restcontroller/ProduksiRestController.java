package com.ta.c02_5.restcontroller;

import com.ta.c02_5.model.RequestUpdateItemModel;
import com.ta.c02_5.rest.RequestUpdateItemDetail;
import com.ta.c02_5.service.ProduksiRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class ProduksiRestController {

    @Autowired
    private ProduksiRestService produksiRestService;

    @PostMapping(value = "/requestupdateitem")
    private RequestUpdateItemModel createProduksi(@Valid @RequestBody RequestUpdateItemDetail rui, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else {
            System.out.println(rui.getId_item()+" "+ rui.getId_kategori()+ " " +rui.getTambahan_stok()+" " + rui.getId_cabang());
            return produksiRestService.createRequestUpdateItem(rui.getId_item(), rui.getId_kategori(), rui.getTambahan_stok(), rui.getId_cabang());
        }
    }
    
}
