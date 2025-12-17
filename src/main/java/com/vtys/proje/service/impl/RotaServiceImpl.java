package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Rota;
import com.vtys.proje.repository.RotaRepository;
import com.vtys.proje.service.RotaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RotaServiceImpl implements RotaService {

    private final RotaRepository repository;

    public RotaServiceImpl(RotaRepository repository) {
        this.repository = repository;
    }

    public Rota save(Rota r) { return repository.save(r); }
    public Rota update(Rota r) { return repository.save(r); }
    public void delete(Integer id) { repository.deleteById(id); }
    public Optional<Rota> findById(Integer id) { return repository.findById(id); }
    public List<Rota> findAll() { return repository.findAll(); }
}
