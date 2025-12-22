package com.vtys.proje.controller;

import com.vtys.proje.entity.Favori;
import com.vtys.proje.repository.FavoriRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity; // Bunu eklemeyi unutma

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/favoriler")
@CrossOrigin
public class FavoriController {

    private final FavoriRepository repo;

    public FavoriController(FavoriRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<?> ekle(@RequestBody Favori f) {
        boolean zateVar = repo.existsByKullanici_KullaniciIdAndRotaPlan_Id(
            f.getKullanici().getKullaniciId(),
            f.getRotaPlan().getId()
        );

        if (zateVar) {
            return ResponseEntity.status(409).body("Bu sefer zaten favorilerinizde ekli!");
        }
        f.setEklenmeTarihi(LocalDate.now());
        Favori yeniFavori = repo.save(f);
        
        return ResponseEntity.ok(yeniFavori);
    }

    @GetMapping("/kullanici/{id}")
    public List<Favori> liste(@PathVariable Integer id) {
        return repo.findByKullanici_KullaniciId(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> sil(@PathVariable Integer id) {
       
        repo.deleteById(id);
        return ResponseEntity.ok("Favori silindi");
    }
}