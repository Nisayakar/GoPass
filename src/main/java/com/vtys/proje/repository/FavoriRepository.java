package com.vtys.proje.repository;

import com.vtys.proje.entity.Favori;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoriRepository extends JpaRepository<Favori, Integer> {

    List<Favori> findByKullanici_KullaniciId(Integer kullaniciId);

    // Controller'da çağırdığınız uzun metodun tanımı:
    boolean existsByKullanici_KullaniciIdAndRota_RotaIdAndFirma_FirmaId(
        Integer kullaniciId, 
        Integer rotaId, 
        Integer firmaId
    );
}