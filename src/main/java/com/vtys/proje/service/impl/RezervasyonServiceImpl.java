package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Firma;
import com.vtys.proje.entity.Koltuk;
import com.vtys.proje.entity.Kullanici;
import com.vtys.proje.entity.Rezervasyon;
import com.vtys.proje.repository.FirmaRepository;
import com.vtys.proje.repository.KoltukRepository;
import com.vtys.proje.repository.KullaniciRepository;
import com.vtys.proje.repository.RezervasyonRepository;
import com.vtys.proje.service.RezervasyonService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RezervasyonServiceImpl implements RezervasyonService {

    private final RezervasyonRepository rezervasyonRepository;
    private final KoltukRepository koltukRepository;

    public RezervasyonServiceImpl(
            RezervasyonRepository rezervasyonRepository,
            KoltukRepository koltukRepository) {
        this.rezervasyonRepository = rezervasyonRepository;
        this.koltukRepository = koltukRepository;
    }

    @Override
    public Rezervasyon save(Rezervasyon r) {

        // 1️⃣ Koltuğu DB'den çek
        Koltuk koltuk = koltukRepository
                .findById(r.getKoltuk().getKoltukId())
                .orElseThrow(() -> new RuntimeException("Koltuk bulunamadı"));

        // 2️⃣ Eğer dolu veya rezerve ise ENGELLE
        if (!koltuk.getDurum().equalsIgnoreCase("Boş")) {
            throw new RuntimeException("Bu koltuk zaten rezerve edilmiş veya dolu");
        }

        // 3️⃣ Rezerve et (kilitle)
        koltuk.setDurum("Rezerve");
        koltukRepository.save(koltuk);

        // 4️⃣ Rezervasyonu kaydet
        return rezervasyonRepository.save(r);
    }



    @Override
    public Rezervasyon update(Rezervasyon r) {
        return rezervasyonRepository.save(r);
    }

    @Override
    public void delete(Integer id) {
    	rezervasyonRepository.deleteById(id);
    }

    @Override
    public Optional<Rezervasyon> findById(Integer id) {
        return rezervasyonRepository.findById(id);
    }

    @Override
    public List<Rezervasyon> findAll() {
        return rezervasyonRepository.findAll();
    }
    
    @Override
    public List<Rezervasyon> findByKullaniciId(Integer kullaniciId) {
        return rezervasyonRepository.findByKullanici_KullaniciId(kullaniciId);
    }

}