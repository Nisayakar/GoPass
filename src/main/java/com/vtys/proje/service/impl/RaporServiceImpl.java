package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Rapor;
import com.vtys.proje.repository.RaporRepository;
import com.vtys.proje.service.RaporService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaporServiceImpl implements RaporService {

    private final RaporRepository repository;

    public RaporServiceImpl(RaporRepository repository) {
        this.repository = repository;
    }

    public Rapor save(Rapor r) { return repository.save(r); }
    public Rapor update(Rapor r) { return repository.save(r); }
    public void delete(Integer id) { repository.deleteById(id); }
    public Optional<Rapor> findById(Integer id) { return repository.findById(id); }
    public List<Rapor> findAll() { return repository.findAll(); }
}
