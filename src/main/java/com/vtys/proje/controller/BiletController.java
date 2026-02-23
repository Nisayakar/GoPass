package com.vtys.proje.controller;

import com.vtys.proje.entity.*;
import com.vtys.proje.repository.*;
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
        return biletRepo.findByRezervasyon_Yolcu_Kullanici_KullaniciId(id);
    }

    @DeleteMapping("/{id}")
    public void iptalEt(@PathVariable Integer id) {
        Optional<Bilet> biletOp = biletRepo.findById(id);
        
        if (biletOp.isPresent()) {
            Bilet bilet = biletOp.get();
            Integer rezId = (bilet.getRezervasyon() != null) ? bilet.getRezervasyon().getRezervasyonId() : null;

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
            if(rezId != null) rezRepo.deleteById(rezId);
        }
    }

    @PostMapping("/satin-al")
    public Bilet satinAl(@RequestBody Bilet bilet) {
        Rezervasyon rez = rezRepo.findById(bilet.getRezervasyon().getRezervasyonId())
                .orElseThrow(() -> new RuntimeException("Rezervasyon bulunamadı"));

        if (!"Beklemede".equals(rez.getDurum()) && !"Rezerve".equals(rez.getDurum())) {
            throw new RuntimeException("Bu rezervasyon işlem yapılamaz durumda!");
        }

        rez.setDurum("Biletlendi");
        rezRepo.save(rez);

        bilet.setOlusturulmaTarihi(LocalDate.now());
        return biletRepo.save(bilet);
    }
}