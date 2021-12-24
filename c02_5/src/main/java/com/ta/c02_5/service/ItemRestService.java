package com.ta.c02_5.service;

import com.ta.c02_5.model.ItemModel;
import com.ta.c02_5.rest.ItemDetail;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

public interface ItemRestService {
    HashMap<String, List<ItemDetail>> getListItem();
    ItemDetail getItemByUUID(String uuid);
    Mono<HashMap> updateStokItem (ItemDetail item);
    List<HashMap<String, Object>> getProposedItemHashMap(ItemModel proposedItem);

    List<HashMap<String, Object>> getProposedItemHashMap(ItemModel proposedItem);

    List<HashMap<String, Object>> getProposedItemHashMap(ItemModel proposedItem);

}
