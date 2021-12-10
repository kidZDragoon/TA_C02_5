package com.ta.c02_5.service;

import com.ta.c02_5.model.MesinModel;

import java.util.List;

public interface MesinService {
    List<MesinModel> getMesinList();
    List<MesinModel> getListMesin();
    List<MesinModel> getListMesinByIdKategori(Integer idKategori);
    MesinModel findByIdMesin(Integer idMesin);
}
