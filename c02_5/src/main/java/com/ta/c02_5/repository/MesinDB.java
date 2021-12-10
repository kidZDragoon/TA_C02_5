package com.ta.c02_5.repository;

import com.ta.c02_5.model.MesinModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MesinDB extends JpaRepository<MesinModel, Integer> {
    Optional<MesinModel> findByIdMesin(Integer idMesin);
}
