package com.ta.c02_5.controller;

import com.ta.c02_5.model.DeliveryModel;
import com.ta.c02_5.model.PegawaiModel;
import com.ta.c02_5.model.RoleModel;
import com.ta.c02_5.service.DeliveryService;
import com.ta.c02_5.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private PegawaiService pegawaiService;

    @GetMapping("/delivery/viewall")
    public String listDelivery(Model model, final HttpServletRequest info) {
        List<DeliveryModel> listDelivery = deliveryService.getDeliveryList();
        String username = info.getUserPrincipal().getName();
        PegawaiModel user = pegawaiService.findByUsername(username);
        String role = user.getRole().getNamaRole();

        if(role.equals("STAFF_OPERASIONAL")){
            model.addAttribute ( "listDelivery",listDelivery);
            return "viewall-delivery" ;
        }
        else {
            model.addAttribute ( "listDelivery",user.getListDelivery());
            return "viewall-delivery-kurir" ;
        }
    }
}
