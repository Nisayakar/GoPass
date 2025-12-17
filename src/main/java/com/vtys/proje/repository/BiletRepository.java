package com.vtys.proje.repository;

import com.vtys.proje.entity.Bilet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BiletRepository extends JpaRepository<Bilet, Integer> {
    List<Bilet> findByKullanici_KullaniciId(Integer kullaniciId);
}
