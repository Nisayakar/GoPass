package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Fatura;
import com.vtys.proje.repository.FaturaRepository;
import com.vtys.proje.service.FaturaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaturaServiceImpl implements FaturaService {

    private final FaturaRepository repository;

    public FaturaServiceImpl(FaturaRepository repository) {
        this.repository = repository;
    }

    public Fatura save(Fatura f) { return repository.save(f); }
    public Fatura update(Fatura f) { return repository.save(f); }
    public void delete(Integer id) { repository.deleteById(id); }
    public Optional<Fatura> findById(Integer id) { return repository.findById(id); }
    public List<Fatura> findAll() { return repository.findAll(); }
}
