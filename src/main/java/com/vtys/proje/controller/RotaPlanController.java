package com.vtys.proje.controller;

import com.vtys.proje.entity.RotaPlan;
import com.vtys.proje.service.RotaPlanService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rota-plan")
@CrossOrigin
public class RotaPlanController {

    private final RotaPlanService service;

    public RotaPlanController(RotaPlanService service) {
        this.service = service;
    }

    @PostMapping
    public RotaPlan ekle(@RequestBody RotaPlan r) {
        return service.save(r);
    }

    @GetMapping
    public List<RotaPlan> listele() {
        return service.findAll();
    }

    @GetMapping("/sefer-ara")
    public List<RotaPlan> seferAra(
            @RequestParam String kalkis,
            @RequestParam String varis,
            @RequestParam String tip,
            @RequestParam(value = "tarih", required = false) String tarihStr 
    ) {
        LocalDate tarih = null;
        if (tarihStr != null && !tarihStr.isEmpty()) {
            try {
                tarih = LocalDate.parse(tarihStr);
            } catch (Exception e) {
                tarih = null;
            }
        }
        
        return service.seferAra(kalkis, varis, tip, tarih);
    }
}
