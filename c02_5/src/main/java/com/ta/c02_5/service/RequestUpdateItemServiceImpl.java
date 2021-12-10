package com.ta.c02_5.service;

import com.ta.c02_5.model.RequestUpdateItemModel;
import com.ta.c02_5.repository.RequestUpdateItemDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RequestUpdateItemServiceImpl implements RequestUpdateItemService {

    @Autowired
    RequestUpdateItemDB requestUpdateItemDB;

    @Override
    public List<RequestUpdateItemModel> getRequestUpdateItemList() {
        return requestUpdateItemDB.findAll();
    }
}


