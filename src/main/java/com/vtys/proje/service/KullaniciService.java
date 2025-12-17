package com.vtys.proje.service;

import com.vtys.proje.entity.Kullanici;

import java.util.List;
import java.util.Optional;

public interface KullaniciService {

 
    Kullanici save(Kullanici k);
    Kullanici update(Kullanici k);
    void delete(Integer id);
    Optional<Kullanici> findById(Integer id);
    List<Kullanici> findAll();


    Optional<Kullanici> login(String eposta, String parola);
}
