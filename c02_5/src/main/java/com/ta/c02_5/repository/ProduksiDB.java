package com.ta.c02_5.repository;

import com.ta.c02_5.model.MesinModel;
import com.ta.c02_5.model.ProduksiModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProduksiDB extends JpaRepository<ProduksiModel, Long> {
    List<ProduksiModel> findByOrderByPegawaiAsc();
    Optional<ProduksiModel> findByIdProduksi(Long idProduksi);
    List<ProduksiModel> findByIdItem (String idItem);
}
