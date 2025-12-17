package com.vtys.proje.controller;

import com.vtys.proje.entity.RezervasyonEkHizmet;
import com.vtys.proje.entity.RezervasyonEkHizmetId;
import com.vtys.proje.service.RezervasyonEkHizmetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rezervasyon-ek-hizmet")
public class RezervasyonEkHizmetController {

    private final RezervasyonEkHizmetService service;

    public RezervasyonEkHizmetController(RezervasyonEkHizmetService service) {
        this.service = service;
    }

    @PostMapping
    public RezervasyonEkHizmet save(@RequestBody RezervasyonEkHizmet r) {
        return service.save(r);
    }

    @GetMapping
    public List<RezervasyonEkHizmet> findAll() {
        return service.findAll();
    }

    @PostMapping("/find")
    public Optional<RezervasyonEkHizmet> findById(@RequestBody RezervasyonEkHizmetId id) {
        return service.findById(id);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody RezervasyonEkHizmetId id) {
        service.delete(id);
    }
}
