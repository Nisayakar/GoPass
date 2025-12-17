package com.vtys.proje.controller;

import com.vtys.proje.entity.Kullanici;
import com.vtys.proje.service.KullaniciService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kullanicilar")
public class KullaniciController {

    private final KullaniciService service;

    public KullaniciController(KullaniciService service) {
        this.service = service;
    }

    @PostMapping
    public Kullanici save(@RequestBody Kullanici kullanici) {
        return service.save(kullanici);
    }

    @GetMapping
    public List<Kullanici> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Kullanici> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public Kullanici update(@RequestBody Kullanici kullanici) {
        return service.save(kullanici);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
