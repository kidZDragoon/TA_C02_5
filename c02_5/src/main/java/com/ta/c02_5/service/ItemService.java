package com.ta.c02_5.service;

import com.ta.c02_5.model.ProduksiModel;
import com.ta.c02_5.rest.ItemDetail;

public interface ItemService {
    ProduksiModel updateItem(ProduksiModel produksi);
    Integer assignKategori(ItemDetail item);
}
