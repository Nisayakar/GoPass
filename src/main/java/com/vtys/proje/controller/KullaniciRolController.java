package com.vtys.proje.controller;

import com.vtys.proje.entity.KullaniciRol;
import com.vtys.proje.entity.KullaniciRolId;
import com.vtys.proje.service.KullaniciRolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kullanici-roller")
public class KullaniciRolController {

    private final KullaniciRolService service;

    public KullaniciRolController(KullaniciRolService service) {
        this.service = service;
    }

    @PostMapping
    public KullaniciRol save(@RequestBody KullaniciRol k) {
        return service.save(k);
    }

    @GetMapping
    public List<KullaniciRol> findAll() {
        return service.findAll();
    }

    @PostMapping("/find")
    public Optional<KullaniciRol> findById(@RequestBody KullaniciRolId id) {
        return service.findById(id);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody KullaniciRolId id) {
        service.delete(id);
    }
}
