package com.ta.c02_5.service;

import com.ta.c02_5.model.MesinModel;
import com.ta.c02_5.model.ProduksiModel;

import java.util.*;

import com.ta.c02_5.model.ProduksiModel;
import com.ta.c02_5.rest.ItemDetail;
import com.ta.c02_5.service.ItemRestService;
import com.ta.c02_5.repository.ProduksiDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProduksiServiceImpl implements ProduksiService {
    @Autowired
    ProduksiDB produksiDB;

    @Autowired
    ItemRestService itemRestService;

    @Override
    public List<ProduksiModel> getProduksiList() {
        HashMap<String, List<ItemDetail>> items = itemRestService.getListItem();
        List<ProduksiModel> listProd = produksiDB.findByOrderByPegawaiAsc();
        ArrayList<String> idList = new ArrayList<String>();

        for (Map.Entry<String, List<ItemDetail>> set : items.entrySet()) {
            for (ItemDetail item : set.getValue()) {
                idList.add(item.getUuid());
            }
        }

        for (ProduksiModel prod : listProd) {
            if (!idList.contains(prod.getIdItem())) {
                produksiDB.delete(prod);
            }
        }

        return listProd;
    }

    @Override
    public List<ProduksiModel> getListProduksiByIdItem(String idItem) {
        return produksiDB.findByIdItem(idItem);
    }

    @Override
    public ProduksiModel getProduksiByIdProduksi(Long idProduksi) {
        Optional<ProduksiModel> produksi = produksiDB.findByIdProduksi(idProduksi);
        if (produksi.isPresent()) {
            return produksi.get();
        }
        return null;
    }

    @Override
    public void addProduksi(ProduksiModel produksi) {
        produksiDB.save(produksi);
    }
}