package com.vtys.proje.controller;

import com.vtys.proje.entity.FirmaYonetici;
import com.vtys.proje.entity.FirmaYoneticiId;
import com.vtys.proje.service.FirmaYoneticiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/firma-yoneticiler")
public class FirmaYoneticiController {

    private final FirmaYoneticiService service;

    public FirmaYoneticiController(FirmaYoneticiService service) {
        this.service = service;
    }

    @PostMapping
    public FirmaYonetici save(@RequestBody FirmaYonetici f) {
        return service.save(f);
    }

    @GetMapping
    public List<FirmaYonetici> findAll() {
        return service.findAll();
    }

    @PostMapping("/find")
    public Optional<FirmaYonetici> findById(@RequestBody FirmaYoneticiId id) {
        return service.findById(id);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody FirmaYoneticiId id) {
        service.delete(id);
    }
}
