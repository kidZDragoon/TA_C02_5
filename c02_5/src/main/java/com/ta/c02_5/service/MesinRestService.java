package com.ta.c02_5.service;

import com.ta.c02_5.model.MesinModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

public interface MesinRestService {
    MesinModel createMesin(MesinModel mesin);
    List<MesinModel> retrieveListMesin();
    MesinModel getMesinByIdMesin(Integer idMesin);
    List<HashMap<String, Object>> getAllMesinHashMap();
}
