package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Bilet;
import com.vtys.proje.repository.BiletRepository;
import com.vtys.proje.service.BiletService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BiletServiceImpl implements BiletService {

    private final BiletRepository repository;

    public BiletServiceImpl(BiletRepository repository) {
        this.repository = repository;
    }

    public Bilet save(Bilet b) { return repository.save(b); }
    public Bilet update(Bilet b) { return repository.save(b); }
    public void delete(Integer id) { repository.deleteById(id); }
    public Optional<Bilet> findById(Integer id) { return repository.findById(id); }
    public List<Bilet> findAll() { return repository.findAll(); }
}
