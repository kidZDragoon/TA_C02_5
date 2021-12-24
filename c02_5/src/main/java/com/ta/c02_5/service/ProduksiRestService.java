package com.ta.c02_5.service;

import com.ta.c02_5.model.RequestUpdateItemModel;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface ProduksiRestService {
    RequestUpdateItemModel createRequestUpdateItem(String id_item, int id_kategori, int tambahan_stok, int id_cabang);
}