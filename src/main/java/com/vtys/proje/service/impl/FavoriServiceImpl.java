package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Favori;
import com.vtys.proje.repository.FavoriRepository;
import com.vtys.proje.service.FavoriService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriServiceImpl implements FavoriService {

    private final FavoriRepository repository;

    public FavoriServiceImpl(FavoriRepository repository) {
        this.repository = repository;
    }

    public Favori save(Favori f) { return repository.save(f); }
    public Favori update(Favori f) { return repository.save(f); }
    public void delete(Integer id) { repository.deleteById(id); }
    public Optional<Favori> findById(Integer id) { return repository.findById(id); }
    public List<Favori> findAll() { return repository.findAll(); }
}
