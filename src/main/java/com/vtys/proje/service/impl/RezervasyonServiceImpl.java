package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Koltuk;
import com.vtys.proje.entity.Rezervasyon;
import com.vtys.proje.repository.KoltukRepository;
import com.vtys.proje.repository.RezervasyonRepository;
import com.vtys.proje.service.RezervasyonService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RezervasyonServiceImpl implements RezervasyonService {

    private final RezervasyonRepository rezervasyonRepository;
    private final KoltukRepository koltukRepository;

    public RezervasyonServiceImpl(RezervasyonRepository rezervasyonRepository, KoltukRepository koltukRepository) {
        this.rezervasyonRepository = rezervasyonRepository;
        this.koltukRepository = koltukRepository;
    }

    @Override
    public Rezervasyon save(Rezervasyon r) {
  
        if (r.getRotaPlan() == null || r.getRotaPlan().getId() == null) {
            throw new RuntimeException("Rezervasyon için sefer (RotaPlan) bilgisi eksik!");
        }

   
        boolean doluMu = rezervasyonRepository.existsByRotaPlan_IdAndKoltuk_KoltukId(
                r.getRotaPlan().getId(), // RotaPlanId nesnesi (Composite Key)
                r.getKoltuk().getKoltukId()
        );

        if (doluMu) {
            throw new RuntimeException("Bu koltuk bu sefer için zaten dolu!");
        }


        
        r.setDurum("Beklemede");
        return rezervasyonRepository.save(r);
    }

    @Override
    public void delete(Integer id) {
        rezervasyonRepository.deleteById(id);
    }

    @Override 
    public Rezervasyon update(Rezervasyon r) { 
        return rezervasyonRepository.save(r); 
    }
    
    @Override 
    public Optional<Rezervasyon> findById(Integer id) { 
        return rezervasyonRepository.findById(id); 
    }
    
    @Override 
    public List<Rezervasyon> findAll() { 
        return rezervasyonRepository.findAll(); 
    }
    
    @Override 
    public List<Rezervasyon> findByKullaniciId(Integer kullaniciId) { 
        return rezervasyonRepository.findByKullanici_KullaniciId(kullaniciId); 
    }
}