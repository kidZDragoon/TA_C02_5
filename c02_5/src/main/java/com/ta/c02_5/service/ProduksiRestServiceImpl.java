package com.ta.c02_5.service;

import com.ta.c02_5.model.RequestUpdateItemModel;
import com.ta.c02_5.repository.RequestUpdateItemDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
@Transactional
public class ProduksiRestServiceImpl implements ProduksiRestService {
//    private final WebClient webClient;
//
//    public ProduksiRestServiceImpl(WebClient.Builder webClientBuilder){
//        this.webClient = webClientBuilder.baseUrl(Setting.produksiUrl).build();
//    }
    
    @Autowired
    private RequestUpdateItemDB requestUpdateItemDB;

    @Override
    public RequestUpdateItemModel createRequestUpdateItem(String id_item, int id_kategori, int tambahan_stok, int id_cabang) {
        RequestUpdateItemModel rui = new RequestUpdateItemModel();
        rui.setIdKategori(id_kategori);
        rui.setTambahanStok(tambahan_stok);
        rui.setIdCabang(id_cabang);
        rui.setIdItem(id_item);
        rui.setExecuted(false);
        Date today = Calendar.getInstance().getTime();
        rui.setTanggalRequest(today);

        return requestUpdateItemDB.save(rui);
    }
}
