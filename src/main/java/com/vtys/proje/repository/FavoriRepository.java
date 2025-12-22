package com.vtys.proje.repository;

import com.vtys.proje.entity.Favori;
import com.vtys.proje.entity.RotaPlanId;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriRepository extends JpaRepository<Favori, Integer> {
    List<Favori> findByKullanici_KullaniciId(Integer kullaniciId);
    
    boolean existsByKullanici_KullaniciIdAndRotaPlan_Id(Integer kullaniciId, RotaPlanId rotaPlanId);
}


