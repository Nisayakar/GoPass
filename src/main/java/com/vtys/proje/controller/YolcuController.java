package com.vtys.proje.controller;

import com.vtys.proje.entity.Yolcu;
import com.vtys.proje.service.YolcuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/yolcular")
public class YolcuController {

    private final YolcuService service;

    public YolcuController(YolcuService service) {
        this.service = service;
    }

    @PostMapping
    public Yolcu save(@RequestBody Yolcu y) {
        return service.save(y);
    }

    @GetMapping
    public List<Yolcu> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Yolcu> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public Yolcu update(@RequestBody Yolcu y) {
        return service.save(y);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
