package com.vtys.proje.controller;

import com.vtys.proje.entity.Koltuk;
import com.vtys.proje.service.KoltukService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/koltuklar")
public class KoltukController {

    private final KoltukService koltukService;

    public KoltukController(KoltukService koltukService) {
        this.koltukService = koltukService;
    }

    @GetMapping("/{rotaPlanId}/koltuklar")
    public List<Koltuk> getKoltuklar(@PathVariable Integer rotaPlanId) {
        return koltukService.getKoltuklarByRotaPlanId(rotaPlanId);
    }
}
