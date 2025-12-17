package com.vtys.proje.controller;

import com.vtys.proje.entity.Bildirim;
import com.vtys.proje.service.BildirimService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bildirimler")
public class BildirimController {

    private final BildirimService service;

    public BildirimController(BildirimService service) {
        this.service = service;
    }

    @PostMapping
    public Bildirim save(@RequestBody Bildirim b) {
        return service.save(b);
    }

    @GetMapping
    public List<Bildirim> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Bildirim> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public Bildirim update(@RequestBody Bildirim b) {
        return service.save(b);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
