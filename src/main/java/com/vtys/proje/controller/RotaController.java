package com.vtys.proje.controller;

import com.vtys.proje.repository.RotaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rota")
@CrossOrigin
public class RotaController {

    private final RotaRepository repo;

    public RotaController(RotaRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/ara")
    public List<Object[]> ara(
            @RequestParam String kalkis,
            @RequestParam String varis
    ) {
        return repo.rotaBul(kalkis, varis);
    }
}
