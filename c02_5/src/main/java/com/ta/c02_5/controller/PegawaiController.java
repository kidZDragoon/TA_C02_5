package com.ta.c02_5.controller;

import com.ta.c02_5.model.PegawaiModel;
import com.ta.c02_5.service.PegawaiService;
import com.ta.c02_5.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        model.addAttribute("listRole", roleService.getListRole());
        return "web-add-pegawai";
    }

    @PostMapping(value = "/addUser")
    private String addUserSubmit(@ModelAttribute PegawaiModel user,HttpServletRequest request, Model model) {
        try {
            Principal principal = request.getUserPrincipal();
            PegawaiModel pegawai = pegawaiService.findByUsername(principal.getName());
            pegawai.setCounter(pegawai.getCounter() + 1);
            System.out.println(pegawai.getCounter());
            user.setCounter(0);
            pegawaiService.addUser(user);
            model.addAttribute("user", user);
            return "redirect:/";
        }catch (DataIntegrityViolationException e){
            return "error";
        }
    }


}
