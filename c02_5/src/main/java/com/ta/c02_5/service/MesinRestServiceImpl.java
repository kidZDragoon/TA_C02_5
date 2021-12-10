package com.ta.c02_5.service;

import com.ta.c02_5.model.MesinModel;
import com.ta.c02_5.repository.MesinDB;
import com.ta.c02_5.rest.MesinDetail;
import com.ta.c02_5.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class MesinRestServiceImpl implements MesinRestService {

    @Autowired
    MesinDB mesinDB;

    @Override
    public MesinModel createMesin(MesinModel mesin) {
        return mesinDB.save(mesin);
    }

    @Override
    public List<MesinModel> retrieveListMesin() {
        return mesinDB.findAll();
    }

    @Override
    public MesinModel getMesinByIdMesin(Integer idMesin) {
        Optional<MesinModel> mesin = mesinDB.findByIdMesin(idMesin);

        if(mesin.isPresent()) {
            return mesin.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<HashMap<String, Object>> getAllMesinHashMap() {
        List<MesinModel> mesinModelList = retrieveListMesin();
        List<HashMap<String, Object>> result = new ArrayList<>();

        for(MesinModel mesin : mesinModelList) {
            HashMap<String, Object> mesinHP = new HashMap<>();
            mesinHP.put("idMesin", mesin.getIdMesin());
            mesinHP.put("nama", mesin.getNama());
            mesinHP.put("idKategori", mesin.getIdKategori());
            mesinHP.put("tanggalDibuat", mesin.getTanggalDibuat());
            mesinHP.put("kapasitas", mesin.getKapasitas());
            result.add(mesinHP);
        }
        return result;
    }
}
