package com.ta.c02_5.controller;

import com.ta.c02_5.model.RequestUpdateItemModel;
import com.ta.c02_5.service.RequestUpdateItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RequestUpdateItemController {

    @Qualifier("requestUpdateItemServiceImpl")
    @Autowired
    private RequestUpdateItemService requestUpdateItemService;

    @RequestMapping("/request-update-item/viewall")
    public String listRequestUpdateItem(Model model) {
        List<RequestUpdateItemModel> listRequestUpdateItem = requestUpdateItemService.getRequestUpdateItemList();
        model.addAttribute("listRequestUpdateItem", listRequestUpdateItem);
        model.addAttribute("params", "request-update-item");

        return "viewall-request-update-item";
    }
}