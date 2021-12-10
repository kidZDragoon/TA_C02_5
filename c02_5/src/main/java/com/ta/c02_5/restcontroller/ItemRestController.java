package com.ta.c02_5.restcontroller;


import com.ta.c02_5.model.ItemModel;
import com.ta.c02_5.rest.ItemDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class ItemRestController {

    @PostMapping(value = "/item/propose")
    public ItemModel proposeItemSubmit(
            @Valid @RequestBody ItemModel item,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            System.out.println(item.getKategori());
            return item;
        }
    }
}
