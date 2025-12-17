package com.vtys.proje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/giris")
    public String giris() {
        return "giris";
    }

    @GetMapping("/kayit")
    public String kayit() {
        return "kayit";
    }

    @GetMapping("/ticket")
    public String ticket() {
        return "ticket";
    }

    @GetMapping("/favorites")
    public String favorites() {
        return "favorites";
    }

    @GetMapping("/reservations")
    public String reservations() {
        return "reservations";
    }
}
