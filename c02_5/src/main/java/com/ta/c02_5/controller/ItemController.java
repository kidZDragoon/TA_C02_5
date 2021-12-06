package com.ta.c02_5.controller;

import com.ta.c02_5.service.ItemRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemRestService itemRestService;

    @GetMapping("/viewall")
    public String getListItem(Model model){
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("role", role);
        model.addAttribute("listItem", itemRestService.getListItem());
        return "viewall-item";
    }

    @GetMapping("/view/{uuid}")
    public String getItemByUUID(Model model, @PathVariable String uuid){
        model.addAttribute("item", itemRestService.getItemByUUID(uuid));
        return "view-byUUID";
    }
}
