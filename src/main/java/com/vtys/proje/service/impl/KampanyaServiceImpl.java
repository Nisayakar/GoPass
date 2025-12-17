package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Kampanya;
import com.vtys.proje.repository.KampanyaRepository;
import com.vtys.proje.service.KampanyaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KampanyaServiceImpl implements KampanyaService {

    private final KampanyaRepository repository;

    public KampanyaServiceImpl(KampanyaRepository repository) {
        this.repository = repository;
    }

    public Kampanya save(Kampanya k) { return repository.save(k); }
    public Kampanya update(Kampanya k) { return repository.save(k); }
    public void delete(Integer id) { repository.deleteById(id); }
    public Optional<Kampanya> findById(Integer id) { return repository.findById(id); }
    public List<Kampanya> findAll() { return repository.findAll(); }
}
