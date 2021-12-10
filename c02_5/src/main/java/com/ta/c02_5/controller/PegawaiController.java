package com.ta.c02_5.controller;

import com.ta.c02_5.model.PegawaiModel;
import com.ta.c02_5.service.PegawaiService;
import com.ta.c02_5.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
@RequestMapping("/user")

public class PegawaiController {

    @Autowired
    private PegawaiService pegawaiService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/addUser")
    private String addUserForm(Model model){
        model.addAttribute("user", new PegawaiModel());
        model.addAttribute("listRole", roleService.getListRole());
        return "web-add-pegawai";
    }

    @PostMapping(value = "/addUser")
    private String addUserSubmit(@ModelAttribute PegawaiModel user, HttpServletRequest request, Model model) {

        Principal principal = request.getUserPrincipal();
        PegawaiModel pegawai = pegawaiService.findByUsername(principal.getName());
        pegawai.setCounter(pegawai.getCounter() + 1);

        if (pegawaiService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("messages", "Username sudah digunakan");
            return "messages";
        }

        user.setCounter(0);
        pegawaiService.addUser(user);

        return "redirect:/";
    }
}
