package com.vtys.proje.controller;

import com.vtys.proje.entity.UlasimTuru;
import com.vtys.proje.service.UlasimTuruService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ulasim-turleri")
public class UlasimTuruController {

    private final UlasimTuruService service;

    public UlasimTuruController(UlasimTuruService service) {
        this.service = service;
    }

    @PostMapping
    public UlasimTuru save(@RequestBody UlasimTuru u) {
        return service.save(u);
    }

    @GetMapping
    public List<UlasimTuru> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UlasimTuru> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public UlasimTuru update(@RequestBody UlasimTuru u) {
        return service.save(u);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
