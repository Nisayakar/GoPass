package com.vtys.proje.controller;

import com.vtys.proje.entity.Indirim;
import com.vtys.proje.service.IndirimService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/indirimler")
public class IndirimController {

    private final IndirimService service;

    public IndirimController(IndirimService service) {
        this.service = service;
    }

    @PostMapping
    public Indirim save(@RequestBody Indirim i) {
        return service.save(i);
    }

    @GetMapping
    public List<Indirim> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Indirim> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public Indirim update(@RequestBody Indirim i) {
        return service.save(i);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
