package com.vtys.proje.repository;

import com.vtys.proje.entity.Odeme;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; // Optional ekledik

public interface OdemeRepository extends JpaRepository<Odeme, Integer> {
   
    Optional<Odeme> findByRezervasyon_RezervasyonId(Integer rezervasyonId);
}