package com.ta.c02_5.controller;

import com.ta.c02_5.model.MesinModel;
import com.ta.c02_5.service.MesinRestService;

import com.ta.c02_5.service.MesinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

@Controller
public class MesinController {
    @Qualifier("mesinServiceImpl")
    @Autowired
    private MesinService mesinService;

    @GetMapping("mesin/list-mesin")
    private String listMesin(Model model) {
        List<MesinModel> listMesin = mesinService.getMesinList();
        model.addAttribute("listMesin", listMesin);
        return "viewall-mesin";
    }
}
