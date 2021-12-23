package com.ta.c02_5.repository;

import com.ta.c02_5.model.PegawaiModel;
import com.ta.c02_5.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PegawaiDB extends JpaRepository<PegawaiModel, String> {
    PegawaiModel findByUsername(String username);
    List<PegawaiModel> findAllByRole(RoleModel role);
}
