package com.vtys.proje.repository;

import com.vtys.proje.entity.Rezervasyon;
import com.vtys.proje.entity.RotaPlanId; // Bu importu eklemeyi unutmayın
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RezervasyonRepository extends JpaRepository<Rezervasyon, Integer> {

    List<Rezervasyon> findByKullanici_KullaniciId(Integer kullaniciId);


    List<Rezervasyon> findByRotaPlan_Id(RotaPlanId id);

    boolean existsByRotaPlan_IdAndKoltuk_KoltukId(RotaPlanId rotaPlanId, Integer koltukId);
}