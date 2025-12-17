package com.vtys.proje.controller;

import com.vtys.proje.entity.Arac;
import com.vtys.proje.service.AracService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/araclar")
public class AracController {

    private final AracService service;

    public AracController(AracService service) {
        this.service = service;
    }

    @PostMapping
    public Arac save(@RequestBody Arac a) {
        return service.save(a);
    }

    @GetMapping
    public List<Arac> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Arac> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public Arac update(@RequestBody Arac a) {
        return service.save(a);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
