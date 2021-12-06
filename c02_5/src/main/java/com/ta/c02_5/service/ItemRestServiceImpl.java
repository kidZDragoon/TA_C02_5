package com.ta.c02_5.service;

import com.ta.c02_5.rest.ItemDetail;
import com.ta.c02_5.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ItemRestServiceImpl implements ItemRestService{
    private final WebClient webClient;

    public ItemRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.itemUrl).build();
    }

    @Override
    public HashMap<String, List<ItemDetail>> getListItem() {
        HashMap<String, Object> response = this.webClient.get().uri("/api/item").retrieve().bodyToMono(HashMap.class).block();
        List<HashMap> data = (List<HashMap>) response.get("result");
        HashMap<String, List<ItemDetail>> res = new HashMap<>();
        groupByCategory(data,res);
        return res;
    }

    public void groupByCategory(List<HashMap> data,HashMap<String, List<ItemDetail>> res ){
        for (int i = 0; i < data.size(); i++) {
            ItemDetail temp = new ItemDetail();
            HashMap itemJson = data.get(i);
            temp.setNama((String)itemJson.get("nama"));
            temp.setUuid((String)itemJson.get("uuid"));
            temp.setKategori((String)itemJson.get("kategori"));
            temp.setHarga((Integer) itemJson.get("harga"));
            temp.setStok((Integer)itemJson.get("stok"));
            if(res.containsKey(temp.getKategori())){
                List<ItemDetail> tempList = res.get(temp.getKategori());
                tempList.add(temp);
                res.put(temp.getKategori(),tempList);
            } else{
                List<ItemDetail> tempList = new ArrayList<>();
                tempList.add(temp);
                res.put(temp.getKategori(),tempList);
            }
        }
    }

    @Override
    public ItemDetail getItemByUUID(String uuid) {
        HashMap<String, Object> response = this.webClient.get().uri("/api/item/"+uuid).retrieve().bodyToMono(HashMap.class).block();
        HashMap res = (HashMap) response.get("result");
        ItemDetail result = new ItemDetail();
        result.setNama((String)res.get("nama"));
        result.setUuid((String)res.get("uuid"));
        result.setKategori((String)res.get("kategori"));
        result.setHarga((Integer) res.get("harga"));
        result.setStok((Integer)res.get("stok"));
        return result;
    }
}
