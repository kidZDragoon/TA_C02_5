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

<<<<<<< HEAD
    List<HashMap<String, Object>> getProposedItemHashMap(ItemModel proposedItem);

<<<<<<< HEAD
    List<HashMap<String, Object>> getProposedItemHashMap(ItemModel proposedItem);

<<<<<<< HEAD
    List<HashMap<String, Object>> getProposedItemHashMap(ItemModel proposedItem);

<<<<<<< HEAD
<<<<<<< HEAD
    List<HashMap<String, Object>> getProposedItemHashMap(ItemModel proposedItem);

<<<<<<< HEAD
=======
    List<HashMap<String, Object>> getProposedItemHashMap(ItemModel proposedItem);

>>>>>>> parent of 02fed9f (Feat/auth (#40))
=======
>>>>>>> parent of 7251d35 (Feat/auth (#38))
=======
>>>>>>> parent of 7251d35 (Feat/auth (#38))
=======
>>>>>>> parent of f88f595 (Feat/auth (#37))
=======
>>>>>>> parent of 575c8b9 (Feat/auth (#36))
=======
>>>>>>> parent of b3ec948 (Feat/auth (#35))
}
