package com.vtys.proje.controller;

import com.vtys.proje.entity.Bilet;
import com.vtys.proje.entity.Fatura;
import com.vtys.proje.entity.Odeme;
import com.vtys.proje.entity.Rezervasyon;
import com.vtys.proje.entity.RezervasyonEkHizmet;
import com.vtys.proje.repository.BiletRepository;
import com.vtys.proje.repository.FaturaRepository;
import com.vtys.proje.repository.OdemeRepository;
import com.vtys.proje.repository.RezervasyonEkHizmetRepository;
import com.vtys.proje.repository.RezervasyonRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bilet")
@CrossOrigin
public class BiletController {

    private final BiletRepository biletRepo;
    private final RezervasyonRepository rezRepo;
    private final OdemeRepository odemeRepo;
    private final FaturaRepository faturaRepo;
    private final RezervasyonEkHizmetRepository ekHizmetRepo;

    public BiletController(BiletRepository biletRepo, 
                           RezervasyonRepository rezRepo, 
                           OdemeRepository odemeRepo,
                           FaturaRepository faturaRepo,
                           RezervasyonEkHizmetRepository ekHizmetRepo) {
        this.biletRepo = biletRepo;
        this.rezRepo = rezRepo;
        this.odemeRepo = odemeRepo;
        this.faturaRepo = faturaRepo;
        this.ekHizmetRepo = ekHizmetRepo;
    }

    @GetMapping("/kullanici/{id}")
    public List<Bilet> getir(@PathVariable Integer id) {
        return biletRepo.findByKullanici_KullaniciId(id);
    }
    
    @DeleteMapping("/{id}")
    public void iptalEt(@PathVariable Integer id) {
        Optional<Bilet> biletOp = biletRepo.findById(id);
        
        if (biletOp.isPresent()) {
            Bilet bilet = biletOp.get();
            Integer rezId = null;

            if(bilet.getRezervasyon() != null) {
                rezId = bilet.getRezervasyon().getRezervasyonId();
            }

            if(rezId != null) {
                Optional<Odeme> odeme = odemeRepo.findByRezervasyon_RezervasyonId(rezId);
                if(odeme.isPresent()) {
                    List<Fatura> faturalar = faturaRepo.findByOdeme_OdemeId(odeme.get().getOdemeId());
                    faturaRepo.deleteAll(faturalar);
                    odemeRepo.delete(odeme.get());
                }
                
                List<RezervasyonEkHizmet> ekHizmetler = ekHizmetRepo.findByRezervasyon_RezervasyonId(rezId);
                ekHizmetRepo.deleteAll(ekHizmetler);
            }
            
            biletRepo.deleteById(id);

            if(rezId != null) {
                rezRepo.deleteById(rezId);
            }
        }
    }
    
    @PostMapping("/satin-al")
    public Bilet satinAl(@RequestBody Bilet bilet) {
        Rezervasyon rez = rezRepo.findById(bilet.getRezervasyon().getRezervasyonId())
                .orElseThrow(() -> new RuntimeException("Rezervasyon bulunamadı"));
        
        // --- EKLENEN KISIM BAŞLANGIÇ ---
        // Rezervasyon durumu "Beklemede" değilse (yani Biletlendi ise) işlemi durdur.
        if (!"Beklemede".equals(rez.getDurum())) {
            throw new RuntimeException("Bu rezervasyon için zaten bilet alınmış veya işlem yapılamaz durumda!");
        }
        // --- EKLENEN KISIM BİTİŞ ---

        rez.setDurum("Biletlendi");
        rezRepo.save(rez);

        bilet.setOlusturulmaTarihi(LocalDate.now());
        bilet.setKullanici(rez.getKullanici());

        return biletRepo.save(bilet);
    }
}