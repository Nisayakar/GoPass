package com.vtys.proje.controller;

import com.vtys.proje.entity.Koltuk;
import com.vtys.proje.repository.KoltukRepository;
import com.vtys.proje.service.KoltukService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/koltuklar")
public class KoltukController {

    private final KoltukService service;
    
    @Autowired 
    private KoltukRepository koltukRepository;

    public KoltukController(KoltukService service) {
        this.service = service;
    }
    
    @GetMapping("/arac/{aracId}")
    public List<Koltuk> getKoltuklarByArac(@PathVariable Integer aracId) {
        return koltukRepository.findByArac_AracIdOrderByKoltukNoAsc(aracId);
    }

    @PostMapping
    public Koltuk save(@RequestBody Koltuk k) {
        return service.save(k);
    }

    @GetMapping
    public List<Koltuk> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Koltuk> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public Koltuk update(@RequestBody Koltuk k) {
        return service.save(k);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
