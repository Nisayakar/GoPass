package com.vtys.proje.controller;

import com.vtys.proje.entity.Fatura;
import com.vtys.proje.service.FaturaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/faturalar")
public class FaturaController {

    private final FaturaService service;

    public FaturaController(FaturaService service) {
        this.service = service;
    }

    @PostMapping
    public Fatura save(@RequestBody Fatura f) {
        return service.save(f);
    }

    @GetMapping
    public List<Fatura> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Fatura> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public Fatura update(@RequestBody Fatura f) {
        return service.save(f);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
