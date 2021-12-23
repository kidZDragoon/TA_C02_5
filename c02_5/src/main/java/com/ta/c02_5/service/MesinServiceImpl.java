package com.ta.c02_5.service;

import com.ta.c02_5.model.MesinModel;
import com.ta.c02_5.model.PegawaiModel;
import com.ta.c02_5.repository.MesinDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class MesinServiceImpl implements MesinService {
    @Autowired
    MesinDB mesinDB;

    @Override
    public List<MesinModel> getMesinList() {
        return mesinDB.findAll();
    }

    @Override
    public List<MesinModel> getListMesinByIdKategori(Integer idKategori) {
        return mesinDB.findByIdKategori(idKategori);
    }

    @Override
    public MesinModel findByIdMesin(Integer idMesin) {
        Optional<MesinModel> mesinModelOptional = mesinDB.findByIdMesin(idMesin);
        MesinModel mesin = mesinModelOptional.get();
        return mesin;
    }

    @Override
    public void updateMesin(MesinModel mesin) {
        mesinDB.save(mesin);
    }

}
