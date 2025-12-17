package com.vtys.proje.controller;

import com.vtys.proje.entity.Kullanici;
import com.vtys.proje.repository.KullaniciRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final KullaniciRepository repo;

    public AuthController(KullaniciRepository repo) {
        this.repo = repo;
    }


    public static class LoginRequest {
        public String email;
        public String password;
        public String eposta;
        public String parola;
    }

    @PostMapping("/login")
    public Kullanici login(@RequestBody LoginRequest req) {
        String eposta = (req.eposta != null && !req.eposta.isBlank()) ? req.eposta : req.email;
        String parola = (req.parola != null && !req.parola.isBlank()) ? req.parola : req.password;

        if (eposta == null || parola == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Eksik bilgi");
        }

        Kullanici k = repo.findByEposta(eposta)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Hatalı giriş"));

        if (!parola.equals(k.getParola())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Hatalı giriş");
        }

        return k;
    }


    public static class RegisterRequest {
        public String fullName;
        public String isim;
        public String soyisim;
        public String email;
        public String eposta;
        public String password;
        public String parola;
    }

    @PostMapping("/register")
    public Kullanici register(@RequestBody RegisterRequest req) {
        String eposta = (req.eposta != null && !req.eposta.isBlank()) ? req.eposta : req.email;
        String parola = (req.parola != null && !req.parola.isBlank()) ? req.parola : req.password;

        if (eposta == null || parola == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Eksik bilgi");
        }

    
        String isim = req.isim;
        String soyisim = req.soyisim;

        if ((isim == null || isim.isBlank()) && req.fullName != null) {
            String[] parts = req.fullName.trim().split("\\s+");
            isim = parts[0];
            soyisim = (parts.length > 1) ? String.join(" ", java.util.Arrays.copyOfRange(parts, 1, parts.length)) : "";
        }

   
        if (repo.findByEposta(eposta).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Bu e-posta zaten kayıtlı");
        }

        Kullanici k = new Kullanici();
        k.setEposta(eposta);
        k.setParola(parola);
        if (isim != null) k.setIsim(isim);
        if (soyisim != null) k.setSoyisim(soyisim);

        return repo.save(k);
    }
}
