package com.ta.c02_5.service;

import com.ta.c02_5.model.DeliveryModel;

import java.util.List;

public interface DeliveryService {
    List<DeliveryModel> getDeliveryList();
    DeliveryModel getDeliveryByIdDelivery(Long idDelivery);
    DeliveryModel getDeliveryByPegawai(Long pegawai);
}
