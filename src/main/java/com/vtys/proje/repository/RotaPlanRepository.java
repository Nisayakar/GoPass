package com.vtys.proje.repository;

import com.vtys.proje.entity.RotaPlan;
import com.vtys.proje.entity.RotaPlanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface RotaPlanRepository extends JpaRepository<RotaPlan, RotaPlanId> {

    // Şehir isimleri üzerinden join yaparak arama
    // Eğer tarih null gönderilirse (opsiyonel) sadece şehirlere göre arar.
    @Query("SELECT rp FROM RotaPlan rp " +
           "WHERE rp.rota.kalkisKonum.sehir = :kalkis " +
           "AND rp.rota.varisKonum.sehir = :varis " +
           "AND (:tarih IS NULL OR rp.seferTarihi = :tarih)")
    List<RotaPlan> aramaYap(
        @Param("kalkis") String kalkis, 
        @Param("varis") String varis, 
        @Param("tarih") LocalDate tarih
    );
}