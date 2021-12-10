package com.ta.c02_5.controller;

import com.ta.c02_5.model.ItemModel;
import com.ta.c02_5.rest.ItemDetail;
import com.ta.c02_5.restcontroller.ItemRestController;
import com.ta.c02_5.service.ItemRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemRestService itemRestService;

    @Autowired
    private ItemRestController itemRestController;

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

    @GetMapping("/propose")
    public String proposeItemForm(Model model) {
        HashMap<String, List<ItemDetail>> allItemHashMap = itemRestService.getListItem();
//        System.out.println(allItemHashMap.keySet());
//        System.out.println(allItemHashMap.get("MAKANAN & MINUMAN").get(0).getKategori());
        model.addAttribute("item", new ItemModel());
        model.addAttribute("listItem", allItemHashMap.keySet());
        return "form-propose-item";
    }

    @PostMapping("/propose")
    public String proposeItemSubmit(
            @ModelAttribute ItemModel item,
            Model model
    ) {
        model.addAttribute("item", item);
        return "submit-propose-item";
    }
}
