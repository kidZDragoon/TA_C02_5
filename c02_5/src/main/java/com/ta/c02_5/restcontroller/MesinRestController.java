package com.ta.c02_5.restcontroller;

import com.ta.c02_5.model.MesinModel;
import com.ta.c02_5.service.MesinRestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class MesinRestController {
    @Autowired
    private MesinRestService mesinRestService;

    @GetMapping(value = "/list-mesin")
    private List<MesinModel> retrieveListMesin() {
        return mesinRestService.retrieveListMesin();
    }
}
