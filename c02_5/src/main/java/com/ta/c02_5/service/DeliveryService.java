package com.ta.c02_5.service;

import com.ta.c02_5.model.DeliveryModel;

import java.util.List;

public interface DeliveryService {
    List<DeliveryModel> getDeliveryList();
    DeliveryModel getDeliveryByIdDelivery(Integer idDelivery);
    DeliveryModel getDeliveryByPegawai(Long pegawai);
    DeliveryModel addDelivery(DeliveryModel deliveryModel);
}
