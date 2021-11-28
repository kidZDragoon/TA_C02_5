package com.ta.c02_5.repository;

import com.ta.c02_5.model.PegawaiModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PegawaiDB extends JpaRepository<PegawaiModel, Long> {
    PegawaiModel findByUsername(String username);
}
