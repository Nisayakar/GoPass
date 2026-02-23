package com.vtys.proje.repository;

import com.vtys.proje.entity.Rezervasyon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RezervasyonRepository extends JpaRepository<Rezervasyon, Integer> {


    List<Rezervasyon> findByYolcu_Kullanici_KullaniciId(Integer kullaniciId);

    @Query("SELECT r FROM Rezervasyon r WHERE r.rotaPlan.rotaPlanId = :rotaPlanId")
    List<Rezervasyon> findByRotaPlanId(@Param("rotaPlanId") Integer rotaPlanId);

    @Query("SELECT r FROM Rezervasyon r WHERE r.rotaPlan.rotaPlanId = :rotaPlanId AND r.koltuk.koltukId = :koltukId")
    Optional<Rezervasyon> findByRotaPlanIdAndKoltukId(@Param("rotaPlanId") Integer rotaPlanId, @Param("koltukId") Integer koltukId);

    @Modifying
    @Transactional
    @Query(value = "CALL sp_bilet_satis(:yolcuId, :planId, :koltukId, CAST(:fiyat AS NUMERIC))", nativeQuery = true)
    void biletSatisIslemi(
        @Param("yolcuId") Integer yolcuId,
        @Param("planId") Integer planId,
        @Param("koltukId") Integer koltukId,
        @Param("fiyat") Double fiyat
    );
}
