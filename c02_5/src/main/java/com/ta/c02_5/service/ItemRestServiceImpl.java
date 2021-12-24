package com.ta.c02_5.service;

import com.ta.c02_5.model.ItemModel;
import com.ta.c02_5.rest.ItemDetail;
import com.ta.c02_5.rest.ProposedItemDetail;
import com.ta.c02_5.rest.Setting;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ItemRestServiceImpl implements ItemRestService{
    private final WebClient webClient;
    private final WebClient webClient2;

    public ItemRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.itemUrl).build();
        this.webClient2 = webClientBuilder.baseUrl(Setting.bisnisUrl).build();
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

    @Override
    public Mono<HashMap> updateStokItem (ItemDetail item) {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("stok", item.getStok());

        try {
            Mono<HashMap> response = this.webClient.put().uri("/api/item/"+ item.getUuid())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(hm), HashMap.class)
                    .accept(MediaType.ALL)
                    .retrieve()
                    .bodyToMono(HashMap.class);

            HashMap res = (HashMap) response.block().get("result");
            ItemDetail result = new ItemDetail();
            result.setStok((Integer)res.get("stok"));

            return response;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<HashMap<String, Object>> getProposedItemHashMap(ItemModel proposedItem) {
        List<HashMap<String, Object>> result = new ArrayList<>();
        HashMap<String, Object> proposedItemHP = new HashMap<>();

        proposedItemHP.put("nama", proposedItem.getNama());
        proposedItemHP.put("harga", proposedItem.getHarga());
        proposedItemHP.put("stok", proposedItem.getStok());
        proposedItemHP.put("kategori", proposedItem.getKategori());

        result.add(proposedItemHP);

        return result;
    }

    @Override
    public Mono<ItemDetail> postItem(ItemModel item) {
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
        data.add("name", item.getNama());
        data.add("stock", item.getStok());
        data.add("price", item.getHarga());
        data.add("category", item.getKategori());

        System.out.println(data);
        return this.webClient2.post().uri("")
                .syncBody(data)
                .retrieve()
                .bodyToMono(ItemDetail.class);
    }
}
