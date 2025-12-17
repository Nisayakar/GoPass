package com.vtys.proje.controller;

import com.vtys.proje.entity.Rol;
import com.vtys.proje.service.RolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roller")
public class RolController {

    private final RolService service;

    public RolController(RolService service) {
        this.service = service;
    }

    @PostMapping
    public Rol save(@RequestBody Rol rol) {
        return service.save(rol);
    }

    @GetMapping
    public List<Rol> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Rol> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public Rol update(@RequestBody Rol rol) {
        return service.save(rol);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
