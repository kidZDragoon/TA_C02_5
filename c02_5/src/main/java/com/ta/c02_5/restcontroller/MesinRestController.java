package com.ta.c02_5.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ta.c02_5.model.MesinModel;
import com.ta.c02_5.service.MesinRestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MesinRestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MesinRestService mesinRestService;

    @RequestMapping("/list-mesin")
    public @ResponseBody String getAllMesinJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<MesinModel> mesinModelList = mesinRestService.retrieveListMesin();
        @SuppressWarnings("unused")
                String exception = null;
                String arrayToJson = null;
                try {
                    arrayToJson = objectMapper.writeValueAsString(mesinModelList);
                } catch (Exception e) {
                    e.printStackTrace();
                    exception = e.getMessage();
                }
                return arrayToJson;

    }

//    @GetMapping(value = "/list-mesin")
//    private List<MesinModel> retrieveListMesin() {
//        return mesinRestService.retrieveListMesin();
//    }

//    @GetMapping(value = "/list-mesin")
//    private List<MesinModel> retrieveListMesin() {
//        List<MesinModel> mesinModelList = mesinRestService.retrieveListMesin();
//        return mesinRestService.retrieveListMesin();
//    }
}
