package com.vtys.proje.repository;

import com.vtys.proje.entity.RotaPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RotaPlanRepository extends JpaRepository<RotaPlan, Integer> {

 
    @Query("""
        SELECT rp FROM RotaPlan rp
        JOIN rp.rota r
        JOIN r.kalkisKonum kk
        JOIN kk.sehir ks
        JOIN r.varisKonum vk
        JOIN vk.sehir vs
        WHERE ks.sehirAdi = :kalkis
          AND vs.sehirAdi = :varis
          AND (:tip = 'all' OR LOWER(rp.arac.ulasimTuru.aracTipi)
               LIKE LOWER(CONCAT('%', :tip, '%')))
          AND (COALESCE(:tarih, rp.seferTarihi) = rp.seferTarihi)
    """)
    List<RotaPlan> seferAra(
            @Param("kalkis") String kalkis,
            @Param("varis") String varis,
            @Param("tip") String tip,
            @Param("tarih") LocalDate tarih
    );

    
    @Query("""
        SELECT rp FROM RotaPlan rp
        JOIN rp.rota r
        JOIN r.kalkisKonum kk
        JOIN kk.sehir ks
        JOIN r.varisKonum vk
        JOIN vk.sehir vs
        WHERE ks.sehirAdi = :kalkis
          AND vs.sehirAdi = :varis
          AND (:tarih IS NULL OR rp.seferTarihi = :tarih)
    """)
    List<RotaPlan> aramaYap(
            @Param("kalkis") String kalkis,
            @Param("varis") String varis,
            @Param("tarih") LocalDate tarih
    );

  
    List<RotaPlan>
    findByRota_KalkisKonum_Sehir_SehirAdiAndRota_VarisKonum_Sehir_SehirAdi(
            String kalkis,
            String varis
    );

    List<RotaPlan>
    findByRota_KalkisKonum_Sehir_SehirAdiAndRota_VarisKonum_Sehir_SehirAdiAndSeferTarihi(
            String kalkis,
            String varis,
            LocalDate seferTarihi
    );
}
