package com.ta.c02_5.service;

import com.ta.c02_5.model.MesinModel;
import com.ta.c02_5.model.PegawaiModel;
import com.ta.c02_5.repository.MesinDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional

public class MesinServiceImpl implements MesinService {
    @Autowired
    private MesinDB mesinDB;

    @Override
    public List<MesinModel> getListMesin() {
        return mesinDB.findAll();
    }

    @Override
    public List<MesinModel> getListMesinByIdKategori(Integer idKategori) {
        return mesinDB.findByIdKategori(idKategori);
    }

    @Override
    public MesinModel findByIdMesin(Integer idMesin) {
        return mesinDB.findByIdMesin(idMesin);
    }
}
