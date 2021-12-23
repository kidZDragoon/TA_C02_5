package com.ta.c02_5.service;

import com.ta.c02_5.model.MesinModel;
import com.ta.c02_5.model.ProduksiModel;

import java.util.List;

public interface ProduksiService {
    List<ProduksiModel> getProduksiList();
    List<ProduksiModel> getListProduksiByIdItem(String idItem);
    ProduksiModel getProduksiByIdProduksi(Long idProduksi);
    void addProduksi(ProduksiModel produksi);
}
