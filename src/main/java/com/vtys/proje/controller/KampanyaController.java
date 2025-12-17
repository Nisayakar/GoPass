package com.vtys.proje.controller;

import com.vtys.proje.entity.Kampanya;
import com.vtys.proje.service.KampanyaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kampanyalar")
public class KampanyaController {

    private final KampanyaService service;

    public KampanyaController(KampanyaService service) {
        this.service = service;
    }

    @PostMapping
    public Kampanya save(@RequestBody Kampanya k) {
        return service.save(k);
    }

    @GetMapping
    public List<Kampanya> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Kampanya> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public Kampanya update(@RequestBody Kampanya k) {
        return service.save(k);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
