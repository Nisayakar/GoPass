package com.vtys.proje.repository;

import com.vtys.proje.entity.Rota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RotaRepository extends JpaRepository<Rota, Integer> {

    @Query(value = """
        SELECT 
            f.firma_adi, 
            ut.aciklama as arac_tipi, 
            k1.sehir as kalkis, 
            k2.sehir as varis, 
            r.km, 
            a.arac_id,
            f.firma_id,
            r.rota_id
        FROM rota r
        JOIN konum k1 ON r.kalkis_konum_id = k1.konum_id
        JOIN konum k2 ON r.varis_konum_id = k2.konum_id
        JOIN rota_plan rp ON r.rota_id = rp.rota_id
        JOIN arac a ON rp.arac_id = a.arac_id
        JOIN firma f ON rp.firma_id = f.firma_id
        JOIN ulasim_turu ut ON a.ulasim_turu_id = ut.ulasim_turu_id
        WHERE k1.sehir ILIKE CONCAT('%', :k, '%') 
          AND k2.sehir ILIKE CONCAT('%', :v, '%')
    """, nativeQuery = true)
    List<Object[]> rotaBul(@Param("k") String kalkis, @Param("v") String varis);
}