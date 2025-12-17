package com.vtys.proje.service.impl;

import com.vtys.proje.entity.FirmaYonetici;
import com.vtys.proje.entity.FirmaYoneticiId;
import com.vtys.proje.repository.FirmaYoneticiRepository;
import com.vtys.proje.service.FirmaYoneticiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FirmaYoneticiServiceImpl implements FirmaYoneticiService {

    private final FirmaYoneticiRepository repository;

    public FirmaYoneticiServiceImpl(FirmaYoneticiRepository repository) {
        this.repository = repository;
    }

    @Override
    public FirmaYonetici save(FirmaYonetici f) {
        return repository.save(f);
    }

    @Override
    public void delete(FirmaYoneticiId id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<FirmaYonetici> findById(FirmaYoneticiId id) {
        return repository.findById(id);
    }

    @Override
    public List<FirmaYonetici> findAll() {
        return repository.findAll();
    }
}
