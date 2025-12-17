package com.vtys.proje.repository;

import com.vtys.proje.entity.Rezervasyon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RezervasyonRepository extends JpaRepository<Rezervasyon, Integer> {

    List<Rezervasyon> findByKullanici_KullaniciId(Integer kullaniciId);
}
