package com.vtys.proje.service.impl;

import com.vtys.proje.entity.RezervasyonEkHizmet;
import com.vtys.proje.entity.RezervasyonEkHizmetId;
import com.vtys.proje.repository.RezervasyonEkHizmetRepository;
import com.vtys.proje.service.RezervasyonEkHizmetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RezervasyonEkHizmetServiceImpl implements RezervasyonEkHizmetService {

    private final RezervasyonEkHizmetRepository repository;

    public RezervasyonEkHizmetServiceImpl(RezervasyonEkHizmetRepository repository) {
        this.repository = repository;
    }

    @Override
    public RezervasyonEkHizmet save(RezervasyonEkHizmet r) {
        return repository.save(r);
    }

    @Override
    public void delete(RezervasyonEkHizmetId id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<RezervasyonEkHizmet> findById(RezervasyonEkHizmetId id) {
        return repository.findById(id);
    }

    @Override
    public List<RezervasyonEkHizmet> findAll() {
        return repository.findAll();
    }
}
