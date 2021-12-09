package com.ta.c02_5.service;
import com.ta.c02_5.model.DeliveryModel;
import java.util.*;

import com.ta.c02_5.model.PegawaiModel;
import com.ta.c02_5.repository.DeliveryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    DeliveryDB deliveryDB;

    @Autowired
    ItemRestService itemRestService;

    @Override
    public List<DeliveryModel> getDeliveryList() {
        List<DeliveryModel> listProd = deliveryDB.findByOrderByPegawaiAsc();
        return listProd;
    }

    @Override
    public DeliveryModel getDeliveryByIdDelivery(Long idDelivery) {
        Optional<DeliveryModel> delivery = deliveryDB.findByIdDelivery(idDelivery);
        if (delivery.isPresent()) {
            return delivery.get();
        }
        return null;
    }

    @Override
    public  DeliveryModel getDeliveryByPegawai(Long pegawai) {
        Optional<DeliveryModel> pegawaiDelivery = deliveryDB.findByPegawai(pegawai);
        if (pegawaiDelivery.isPresent()) {
            return pegawaiDelivery.get();
        }
        return null;
    }
}