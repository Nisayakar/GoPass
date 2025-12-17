package com.vtys.proje.controller;

import com.vtys.proje.entity.Bilet;
import com.vtys.proje.repository.BiletRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bilet")
@CrossOrigin // Frontend erişimi için gerekli
public class BiletController {

    private final BiletRepository repo;

    public BiletController(BiletRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/kullanici/{id}")
    public List<Bilet> getir(@PathVariable Integer id) {
        return repo.findByKullanici_KullaniciId(id);
    }
    
    // YENİ EKLENEN KISIM: Bilet İptali
    @DeleteMapping("/{id}")
    public void iptalEt(@PathVariable Integer id) {
        repo.deleteById(id);
    }
    
    // YENİ EKLENEN KISIM: Rezervasyonu Bilete Dönüştürme (Satın Alma)
    @PostMapping("/satin-al")
    public Bilet satinAl(@RequestBody Bilet bilet) {
        // Burada normalde ödeme işlemleri yapılır.
        // Biz direkt bileti kaydediyoruz.
        return repo.save(bilet);
    }
}