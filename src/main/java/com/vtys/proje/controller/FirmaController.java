package com.vtys.proje.controller;

import com.vtys.proje.entity.Firma;
import com.vtys.proje.service.FirmaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/firmalar")
public class FirmaController {

    private final FirmaService service;

    public FirmaController(FirmaService service) {
        this.service = service;
    }

    @PostMapping
    public Firma save(@RequestBody Firma f) {
        return service.save(f);
    }

    @GetMapping
    public List<Firma> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Firma> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public Firma update(@RequestBody Firma f) {
        return service.save(f);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
