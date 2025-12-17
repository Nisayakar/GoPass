package com.vtys.proje.controller;

import com.vtys.proje.entity.Odeme;
import com.vtys.proje.service.OdemeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/odemeler")
public class OdemeController {

    private final OdemeService service;

    public OdemeController(OdemeService service) {
        this.service = service;
    }

    @PostMapping
    public Odeme save(@RequestBody Odeme o) {
        return service.save(o);
    }

    @GetMapping
    public List<Odeme> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Odeme> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public Odeme update(@RequestBody Odeme o) {
        return service.save(o);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
