package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Rezervasyon;
import com.vtys.proje.repository.RezervasyonRepository;
import com.vtys.proje.service.RezervasyonService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RezervasyonServiceImpl implements RezervasyonService {

    private final RezervasyonRepository repository;
    private final JdbcTemplate jdbcTemplate;

    public RezervasyonServiceImpl(RezervasyonRepository repository, JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Rezervasyon save(Rezervasyon rezervasyon) {
        if (rezervasyon == null) {
            throw new RuntimeException("Rezervasyon nesnesi null gönderilemez!");
        }
        
        if (rezervasyon.getYolcu() == null) {
            throw new RuntimeException("Rezervasyon içinde Yolcu nesnesi bulunamadı!");
        }

        if (rezervasyon.getRotaPlan() == null || rezervasyon.getKoltuk() == null) {
            throw new RuntimeException("Rota Planı veya Koltuk bilgisi eksik!");
        }

        Optional<Rezervasyon> mevcut = repository.findByRotaPlanIdAndKoltukId(
            rezervasyon.getRotaPlan().getRotaPlanId(),
            rezervasyon.getKoltuk().getKoltukId()
        );

        if (mevcut.isPresent() && !"İptal".equals(mevcut.get().getDurum())) {
            throw new RuntimeException("Bu koltuk bu sefer için zaten dolu!");
        }

        if ("Biletlendi".equals(rezervasyon.getDurum())) {
            Double fiyat = (rezervasyon.getFiyat() != null) ? rezervasyon.getFiyat().doubleValue() : 0.0;
            
            String sql = "CALL sp_bilet_satis(?, ?, ?, CAST(? AS NUMERIC))";
            
            Integer yolcuId = rezervasyon.getYolcu().getYolcuId();
            Integer rotaPlanId = rezervasyon.getRotaPlan().getRotaPlanId();
            Integer koltukId = rezervasyon.getKoltuk().getKoltukId();

            jdbcTemplate.update(sql, yolcuId, rotaPlanId, koltukId, fiyat);
            return rezervasyon; 
        } else {
            return repository.save(rezervasyon);
        }
    }

    @Override
    public List<Rezervasyon> findByKullaniciId(Integer kullaniciId) {
        return repository.findByYolcu_Kullanici_KullaniciId(kullaniciId);
    }

    @Override
    public List<Rezervasyon> findByRotaPlanId(Integer rotaPlanId) {
        return repository.findByRotaPlanId(rotaPlanId);
    }

    @Override
    public Rezervasyon update(Rezervasyon r) { return repository.save(r); }

    @Override
    public void delete(Integer id) { repository.deleteById(id); }

    @Override
    public Optional<Rezervasyon> findById(Integer id) { return repository.findById(id); }

    @Override
    public List<Rezervasyon> findAll() { return repository.findAll(); }

    @Override
    public void iptalEt(Integer id) {
        repository.findById(id).ifPresentOrElse(rez -> {
            rez.setDurum("İptal");
            repository.save(rez);
        }, () -> { throw new RuntimeException("Rezervasyon bulunamadı"); });
    }
}
