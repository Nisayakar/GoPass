package com.vtys.proje.controller;

import com.vtys.proje.entity.Konum;
import com.vtys.proje.service.KonumService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/konumlar")
public class KonumController {

    private final KonumService service;

    public KonumController(KonumService service) {
        this.service = service;
    }

    @PostMapping
    public Konum save(@RequestBody Konum k) {
        return service.save(k);
    }

    @GetMapping
    public List<Konum> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Konum> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public Konum update(@RequestBody Konum k) {
        return service.save(k);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
