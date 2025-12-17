package com.vtys.proje.controller;

import com.vtys.proje.entity.BiletIndirim;
import com.vtys.proje.entity.BiletIndirimId;
import com.vtys.proje.service.BiletIndirimService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bilet-indirim")
public class BiletIndirimController {

    private final BiletIndirimService service;

    public BiletIndirimController(BiletIndirimService service) {
        this.service = service;
    }

    @PostMapping
    public BiletIndirim save(@RequestBody BiletIndirim b) {
        return service.save(b);
    }

    @GetMapping
    public List<BiletIndirim> findAll() {
        return service.findAll();
    }

    @PostMapping("/find")
    public Optional<BiletIndirim> findById(@RequestBody BiletIndirimId id) {
        return service.findById(id);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody BiletIndirimId id) {
        service.delete(id);
    }
}
