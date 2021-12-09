package com.ta.c02_5.service;

import com.ta.c02_5.model.ProduksiModel;
import com.ta.c02_5.repository.MesinDB;
import com.ta.c02_5.repository.ProduksiDB;
import com.ta.c02_5.rest.ItemDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional

public class ItemServiceImpl implements ItemService {

    @Autowired
    private ProduksiDB produksiDB;

    @Override
    public ProduksiModel updateItem(ProduksiModel produksi) {
        return produksiDB.save(produksi);
    }

    @Override
    public Integer assignKategori(ItemDetail item) {
        HashMap<String,Integer> kategori = new HashMap<>();
        kategori.put("BUKU",1);
        kategori.put("DAPUR",2);
        kategori.put("MAKANAN & MINUMAN",3);
        kategori.put("ELEKTRONIK",4);
        kategori.put("FASHION",5);
        kategori.put("KECANTIKAN & PERAWATAN DIRI",6);
        kategori.put("GAMING",7);
        kategori.put("GADGET",8);
        kategori.put("KESEHATAN",9);
        kategori.put("RUMAH TANGGA",10);
        kategori.put("FURNITURE",11);
        kategori.put("ALAT & PERANGKAT KERAS",12);
        kategori.put("WEDDING",13);
        kategori.put("FILM & MUSIK",14);

        int idKategori = kategori.get(item.getKategori());

        return idKategori ;
    }
}
