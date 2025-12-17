package com.vtys.proje.controller;

import com.vtys.proje.entity.RotaPlan;
import com.vtys.proje.entity.RotaPlanId;
import com.vtys.proje.service.RotaPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/rota-plan")
public class RotaPlanController {

    private final RotaPlanService service;

    public RotaPlanController(RotaPlanService service) {
        this.service = service;
    }

    @PostMapping
    public RotaPlan save(@RequestBody RotaPlan r) {
        return service.save(r);
    }

    @GetMapping
    public List<RotaPlan> findAll() {
        return service.findAll();
    }

    @PostMapping("/find")
    public Optional<RotaPlan> findById(@RequestBody RotaPlanId id) {
        return service.findById(id);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody RotaPlanId id) {
        service.delete(id);
    }
    
    @GetMapping("/ara")
    public List<RotaPlan> ara(
            @RequestParam String kalkis,
            @RequestParam String varis,
            @RequestParam(required = false) String tarih) { // Tarih artık zorunlu değil (required=false)
        
        LocalDate tarihDate = (tarih != null && !tarih.isEmpty()) ? LocalDate.parse(tarih) : null;
        
        // Servis katmanında bu metodu tanımlamalısınız. 
        // Eğer servis katmanını güncellemek istemezseniz geçici olarak:
        // return repository.aramaYap(kalkis, varis, tarihDate); 
        // şeklinde kullanabilmek için Controller'a repository inject etmelisiniz.
        
        return service.sefereGoreAra(kalkis, varis, tarihDate);
    }
}
