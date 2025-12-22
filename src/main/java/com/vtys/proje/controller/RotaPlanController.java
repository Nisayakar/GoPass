package com.vtys.proje.controller;

import com.vtys.proje.entity.RotaPlan;
import com.vtys.proje.service.RotaPlanService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
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

    @GetMapping("/ara")
    public List<RotaPlan> ara(
            @RequestParam String kalkis,
            @RequestParam String varis,
            @RequestParam(required = false) String tarih) {

        LocalDate searchDate = null;

        if (tarih != null && !tarih.trim().isEmpty()) {
            try {
                searchDate = LocalDate.parse(tarih);
            } catch (DateTimeParseException e) {
                return Collections.emptyList();
            }
        }

        // ARTIK SERVICE METODUNU ÇAĞIRIYORUZ
        return service.sefereGoreAra(kalkis, varis, searchDate);
    }
}