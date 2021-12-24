package com.ta.c02_5.restcontroller;
import com.ta.c02_5.model.DeliveryModel;
import com.ta.c02_5.model.PegawaiModel;
import com.ta.c02_5.rest.DeliveryDetail;
import com.ta.c02_5.rest.Setting;
import com.ta.c02_5.service.DeliveryService;
import com.ta.c02_5.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/api")
public class DeliveryRestController {
    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private PegawaiService pegawaiService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/daftar-alamat-cabang/{idDelivery}/{idCabang}")
    private String getIdCabang(
            @PathVariable Integer idDelivery,
            @PathVariable String idCabang,
            Model model
    ) {
        DeliveryModel deliveryModel = deliveryService.getDeliveryByIdDelivery(idDelivery);
        PegawaiModel pegawaiModel = deliveryModel.getPegawai();
        String retailUrl = Setting.retailUrl;
        HashMap api = restTemplate.getForObject(retailUrl, HashMap.class);
        List listCabangRest = (List) api.get("result");
        List<DeliveryDetail> cabangDelivery = new ArrayList<>();
        Integer counter = pegawaiModel.getCounter();

        for(int i=0; i<listCabangRest.size(); i++) {
            DeliveryDetail deliveryDetail = new DeliveryDetail();
            LinkedHashMap<String, Object> firstHash = (LinkedHashMap<String, Object>) listCabangRest.get(i);
            // LinkedHashMap<String, Object> secondHash = (LinkedHashMap<String, Object>) firstHash.get("result");
            String id_cabang = String.valueOf(firstHash.get("idCabang"));
            String alamat_cabang = String.valueOf(firstHash.get("alamat"));
            deliveryDetail.setIdCabang(id_cabang);
            deliveryDetail.setAlamat(alamat_cabang);
            cabangDelivery.add(deliveryDetail);
        }
        for(DeliveryDetail deliveryDetail:cabangDelivery) {
            if(idCabang.equals(deliveryDetail.getIdCabang())) {
                deliveryModel.setSent(true);
                deliveryModel.setTanggalDikirim(new java.util.Date());
                deliveryService.addDelivery(deliveryModel);
                pegawaiModel.setCounter(pegawaiModel.getCounter() + 1);
                pegawaiService.updatePegawai(pegawaiModel);
                model.addAttribute("deliveryDetail", deliveryDetail);
                model.addAttribute("deliveryModel", deliveryModel);
                return "cabang-found";
            }
        }
        return "cabang-not-found";
    }

//    @Bean
//    public RestTemplate getRestTemplate(){
//        return new RestTemplate();
//    }
}
