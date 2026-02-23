package com.vtys.proje.service;

import com.vtys.proje.entity.Rezervasyon;
import java.util.List;
import java.util.Optional;

public interface RezervasyonService {

    Rezervasyon save(Rezervasyon r);

    Rezervasyon update(Rezervasyon r);

    void delete(Integer id);

    Optional<Rezervasyon> findById(Integer id);

    List<Rezervasyon> findAll();

    List<Rezervasyon> findByKullaniciId(Integer kullaniciId);

    List<Rezervasyon> findByRotaPlanId(Integer rotaPlanId);


    void iptalEt(Integer id);
}
