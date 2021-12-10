package com.ta.c02_5.service;

import com.ta.c02_5.model.ProduksiModel;
import com.ta.c02_5.model.RequestUpdateItemModel;
import com.ta.c02_5.repository.RequestUpdateItemDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RequestUpdateItemServiceImpl implements RequestUpdateItemService {

    @Autowired
    RequestUpdateItemDB requestUpdateItemDB;

    @Override
    public List<RequestUpdateItemModel> getRequestUpdateItemList() {
        return requestUpdateItemDB.findAll();
    }

    @Override
    public RequestUpdateItemModel getRequestUpdateItemByIdRequestUpdateItem(Integer idRequestUpdateItem) {
        Optional<RequestUpdateItemModel> requestUpdateItem = requestUpdateItemDB.findByIdRequestUpdateItem(idRequestUpdateItem);
        if (requestUpdateItem.isPresent()) {
            return requestUpdateItem.get();
        }
        return null;
    }

    @Override
    public RequestUpdateItemModel updateRequestItemModel(RequestUpdateItemModel requestUpdateItemModel) {
        return requestUpdateItemDB.save(requestUpdateItemModel);
    }
}


