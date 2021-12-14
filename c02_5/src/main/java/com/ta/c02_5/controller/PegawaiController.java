package com.ta.c02_5.controller;

import com.ta.c02_5.model.PegawaiModel;
import com.ta.c02_5.service.PegawaiService;
import com.ta.c02_5.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


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

    @GetMapping("/viewall")
    public String listPegawai(Model model){
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        List<PegawaiModel> listPegawai = pegawaiService.getListUser();
        Integer gaji = 0;
        List<Integer> listGaji = new ArrayList<>();
        for (PegawaiModel x:listPegawai) {
            gaji = pegawaiService.getGajiPegawai(x);
            listGaji.add(gaji);
        }
        model.addAttribute("listPegawai", listPegawai);
        model.addAttribute("role",role);
        model.addAttribute("listGaji", listGaji);
        return "viewall-pegawai";
    }
}
