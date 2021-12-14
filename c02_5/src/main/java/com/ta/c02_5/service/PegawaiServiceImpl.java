package com.ta.c02_5.service;

import com.ta.c02_5.model.PegawaiModel;
import com.ta.c02_5.repository.PegawaiDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class PegawaiServiceImpl implements PegawaiService{

    @Autowired
    private PegawaiDB pegawaiDB;

    @Override
    public PegawaiModel addUser(PegawaiModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return pegawaiDB.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public PegawaiModel findByUsername(String username) {
        return pegawaiDB.findByUsername(username);
    }

    @Override
    public List<PegawaiModel> getListUser() {
        return pegawaiDB.findAll();
    }

    @Override
    public Integer getGajiPegawai(PegawaiModel x) {
        Integer gaji = (x.getCounter() * x.getRole().getBaseWages());
        return gaji;
    }
}
