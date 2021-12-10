package com.ta.c02_5.restcontroller;

import com.ta.c02_5.model.MesinModel;
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
import java.util.List;

@RestController
@RequestMapping("/api")
public class MesinRestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MesinRestService mesinRestService;

    @RequestMapping(
            value = "/list-mesin",
            method = RequestMethod.GET,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public ResponseEntity<Iterable<MesinModel>> getAllMesinJSON() {
        try {
            return new ResponseEntity<>(mesinRestService.retrieveListMesin(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
