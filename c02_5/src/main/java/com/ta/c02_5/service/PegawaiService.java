package com.ta.c02_5.service;

import com.ta.c02_5.model.PegawaiModel;

import java.util.List;

public interface PegawaiService {
    PegawaiModel addUser(PegawaiModel user);
    public String encrypt (String password);
    PegawaiModel findByUsername(String username);
    List<PegawaiModel> getListUser();
    Integer getGajiPegawai(PegawaiModel x);
    void updatePegawai(PegawaiModel pegawai);
}
