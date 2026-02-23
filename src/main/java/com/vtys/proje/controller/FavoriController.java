package com.vtys.proje.controller;

import com.vtys.proje.entity.Favori;
import com.vtys.proje.repository.FavoriRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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

     
        if (f.getKullanici() == null || f.getRota() == null || f.getFirma() == null) {
            return ResponseEntity.badRequest().body("Eksik favori bilgisi");
        }

    
        boolean zatenVar = repo.existsByKullanici_KullaniciIdAndRota_RotaIdAndFirma_FirmaId(
                f.getKullanici().getKullaniciId(),
                f.getRota().getRotaId(),
                f.getFirma().getFirmaId()
        );

        if (zatenVar) {
            return ResponseEntity.status(409).body("Bu rota ve firma zaten favorilerinizde");
        }

        f.setEklenmeTarihi(LocalDate.now());
        return ResponseEntity.ok(repo.save(f));
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
