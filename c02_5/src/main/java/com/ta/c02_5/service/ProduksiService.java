package com.ta.c02_5.service;

import com.ta.c02_5.model.ProduksiModel;

import java.util.List;

public interface ProduksiService {
    List<ProduksiModel> getProduksiList();
    ProduksiModel getProduksiByIdProduksi(Long idProduksi);
}
