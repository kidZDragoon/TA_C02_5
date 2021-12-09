package com.ta.c02_5.repository;


import com.ta.c02_5.model.ProduksiModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduksiDB extends JpaRepository<ProduksiModel, Long> {

}
