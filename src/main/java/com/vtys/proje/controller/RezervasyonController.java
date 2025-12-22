package com.vtys.proje.controller;

import com.vtys.proje.entity.Rezervasyon;
import com.vtys.proje.entity.RotaPlanId; // EKLENDİ
import com.vtys.proje.repository.RezervasyonRepository; // EKLENDİ
import com.vtys.proje.service.RezervasyonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rezervasyonlar")
@CrossOrigin 
public class RezervasyonController {

    private final RezervasyonService service;
    private final RezervasyonRepository repo; 


    public RezervasyonController(RezervasyonService service, RezervasyonRepository repo) {
        this.service = service;
        this.repo = repo;
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


    @GetMapping("/rota-plan/{rotaId}/{aracId}/{firmaId}")
    public List<Rezervasyon> getByRotaPlan(
            @PathVariable Integer rotaId,
            @PathVariable Integer aracId,
            @PathVariable Integer firmaId) {
        
     
        RotaPlanId id = new RotaPlanId(rotaId, aracId, firmaId);
        
     
        return repo.findByRotaPlan_Id(id);
    }
}