package com.vtys.proje.controller;

import com.vtys.proje.entity.Favori;
import com.vtys.proje.repository.FavoriRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/favori")
@CrossOrigin
public class FavoriController {

    private final FavoriRepository repo;

    public FavoriController(FavoriRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Favori ekle(@RequestBody Favori f) {
        f.setEklenmeTarihi(LocalDate.now());
        return repo.save(f);
    }

    @GetMapping("/kullanici/{id}")
    public List<Favori> liste(@PathVariable Integer id) {
        return repo.findByKullanici_KullaniciId(id);
    }
}
