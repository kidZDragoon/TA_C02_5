package com.ta.c02_5.restcontroller;


import com.ta.c02_5.model.ItemModel;
import com.ta.c02_5.model.PegawaiModel;
import com.ta.c02_5.rest.ItemDetail;
import com.ta.c02_5.rest.MesinDetail;
import com.ta.c02_5.rest.ProposedItemDetail;
import com.ta.c02_5.service.ItemRestService;
import com.ta.c02_5.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class ItemRestController {

    @Autowired
    private ItemRestService itemRestService;

    @Autowired
    private PegawaiService pegawaiService;

    @PostMapping(
            value = "/item/propose",
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json")
    public ProposedItemDetail<List<HashMap<String, Object>>> proposeItemSubmit(
            @ModelAttribute ItemModel item,
            HttpServletRequest request,
            Model model
    ) {
        ProposedItemDetail<List<HashMap<String, Object>>> response = new ProposedItemDetail<>();
        response.setMessage("success");
        response.setStatus(200);
        response.setResult(itemRestService.getProposedItemHashMap(item));

        //menambahkan counter pada pegawai
        Principal principal = request.getUserPrincipal();
        PegawaiModel pegawai = pegawaiService.findByUsername(principal.getName());
        pegawai.setCounter(pegawai.getCounter() + 1);
        pegawaiService.updatePegawai(pegawai);


        return response;
    }
}
