package com.vtys.proje.controller;

import com.vtys.proje.entity.EkHizmet;
import com.vtys.proje.service.EkHizmetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ek-hizmetler")
public class EkHizmetController {

    private final EkHizmetService service;

    public EkHizmetController(EkHizmetService service) {
        this.service = service;
    }

    @PostMapping
    public EkHizmet save(@RequestBody EkHizmet e) {
        return service.save(e);
    }

    @GetMapping
    public List<EkHizmet> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<EkHizmet> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public EkHizmet update(@RequestBody EkHizmet e) {
        return service.save(e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
