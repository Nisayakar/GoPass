package com.vtys.proje.service.impl;

import com.vtys.proje.entity.UlasimTuru;
import com.vtys.proje.repository.UlasimTuruRepository;
import com.vtys.proje.service.UlasimTuruService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UlasimTuruServiceImpl implements UlasimTuruService {

    private final UlasimTuruRepository repository;

    public UlasimTuruServiceImpl(UlasimTuruRepository repository) {
        this.repository = repository;
    }

    public UlasimTuru save(UlasimTuru u) { return repository.save(u); }
    public UlasimTuru update(UlasimTuru u) { return repository.save(u); }
    public void delete(Integer id) { repository.deleteById(id); }
    public Optional<UlasimTuru> findById(Integer id) { return repository.findById(id); }
    public List<UlasimTuru> findAll() { return repository.findAll(); }
}
