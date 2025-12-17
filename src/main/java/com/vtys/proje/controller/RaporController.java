package com.vtys.proje.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/raporlar")
public class RaporController {

    @GetMapping("/firma-performans")
    public String firmaPerformans() {
        return "Firma performans raporu";
    }

    @GetMapping("/arac-doluluk")
    public String aracDoluluk() {
        return "Araç doluluk raporu";
    }
}
