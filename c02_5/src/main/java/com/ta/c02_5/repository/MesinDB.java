package com.ta.c02_5.repository;

import com.ta.c02_5.model.MesinModel;
import com.ta.c02_5.model.PegawaiModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MesinDB extends JpaRepository<MesinModel, Integer> {
    List<MesinModel> findByIdKategori (Integer idKategori);
    MesinModel findByIdMesin(Integer idMesin);
}
