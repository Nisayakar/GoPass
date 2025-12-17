package com.vtys.proje.controller;

import com.vtys.proje.entity.IptalPolitikasi;
import com.vtys.proje.service.IptalPolitikasiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/iptal-politikalari")
public class IptalPolitikasiController {

    private final IptalPolitikasiService service;

    public IptalPolitikasiController(IptalPolitikasiService service) {
        this.service = service;
    }

    @PostMapping
    public IptalPolitikasi save(@RequestBody IptalPolitikasi i) {
        return service.save(i);
    }

    @GetMapping
    public List<IptalPolitikasi> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<IptalPolitikasi> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public IptalPolitikasi update(@RequestBody IptalPolitikasi i) {
        return service.save(i);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
