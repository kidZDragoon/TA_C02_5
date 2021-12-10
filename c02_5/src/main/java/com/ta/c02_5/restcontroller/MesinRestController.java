package com.ta.c02_5.restcontroller;

import com.ta.c02_5.model.MesinModel;
import com.ta.c02_5.rest.MesinDetail;
import com.ta.c02_5.service.MesinRestService;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

@RestController
@RequestMapping("/api")
public class MesinRestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MesinRestService mesinRestService;

    @GetMapping(
            value = "/list-mesin",
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public MesinDetail<List<MesinModel>> getAllMesinJSON() {
        MesinDetail<List<MesinModel>> response = new MesinDetail<>();
        response.setMessage("success");
        response.setStatus(200);
        response.setResult(mesinRestService.getAllMesinHashMap());
        return response;

    }

//    @GetMapping(
//            value = "/list-mesin",
//            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
//            headers = "Accept=application/json"
//    )
//    public MesinDetail<List<TreeMap<String,Object>>> getAllMesinJSON() {
//        MesinDetail<List<TreeMap<String,Object>>> response = new MesinDetail<>();
//        response.setMessage("success");
//        response.setStatus(200);
//        response.setResult(mesinRestService.getAllMesinHashMap());
//        return response;
//
//    }
}
