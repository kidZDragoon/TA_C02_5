package com.ta.c02_5.repository;

import com.ta.c02_5.model.DeliveryModel;
import com.ta.c02_5.model.PegawaiModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryDB extends JpaRepository<DeliveryModel, Long> {
    List<DeliveryModel> findByOrderByPegawaiAsc();
    Optional<DeliveryModel> findByIdDelivery(Integer idDelivery);
    Optional<DeliveryModel> findByPegawai(Long pegawai);
}