package com.ta.c02_5.service;

import com.ta.c02_5.model.RequestUpdateItemModel;

import java.util.List;

public interface RequestUpdateItemService {
    List<RequestUpdateItemModel> getRequestUpdateItemList();
    RequestUpdateItemModel getRequestUpdateItemByIdRequestUpdateItem(Integer idRequestUpdateItem);
    RequestUpdateItemModel updateRequestItemModel(RequestUpdateItemModel requestUpdateItemModel);
}
