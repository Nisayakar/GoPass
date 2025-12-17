package com.vtys.proje.controller;

import com.vtys.proje.entity.Rezervasyon;
import com.vtys.proje.service.RezervasyonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rezervasyonlar")
@CrossOrigin // CORS hatası almamak için
public class RezervasyonController {

    private final RezervasyonService service;

    public RezervasyonController(RezervasyonService service) {
        this.service = service;
    }

    @PostMapping
    public Rezervasyon save(@RequestBody Rezervasyon r) {
        return service.save(r);
    }

    @GetMapping
    public List<Rezervasyon> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Rezervasyon> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public Rezervasyon update(@RequestBody Rezervasyon r) {
        return service.update(r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
    
    @GetMapping("/kullanici/{id}")
    public List<Rezervasyon> getByKullanici(@PathVariable Integer id) {
        return service.findByKullaniciId(id);
    }
}