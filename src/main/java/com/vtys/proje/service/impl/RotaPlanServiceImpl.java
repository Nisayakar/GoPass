package com.vtys.proje.service.impl;

import com.vtys.proje.entity.RotaPlan;
import com.vtys.proje.entity.RotaPlanId;
import com.vtys.proje.repository.RotaPlanRepository;
import com.vtys.proje.service.RotaPlanService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RotaPlanServiceImpl implements RotaPlanService {

    private final RotaPlanRepository repository;

    public RotaPlanServiceImpl(RotaPlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public RotaPlan save(RotaPlan r) {
        return repository.save(r);
    }

    @Override
    public RotaPlan update(RotaPlan r) {
        return repository.save(r);
    }

    @Override
    public void delete(RotaPlanId id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<RotaPlan> findById(RotaPlanId id) {
        return repository.findById(id);
    }

    @Override
    public List<RotaPlan> findAll() {
        return repository.findAll();
    }

    // Eklenen metodun uygulaması
    @Override
    public List<RotaPlan> sefereGoreAra(String kalkis, String varis, LocalDate tarih) {
        return repository.aramaYap(kalkis, varis, tarih);
    }
}