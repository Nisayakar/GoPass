package com.vtys.proje.service.impl;

import com.vtys.proje.entity.BiletIndirim;
import com.vtys.proje.entity.BiletIndirimId;
import com.vtys.proje.repository.BiletIndirimRepository;
import com.vtys.proje.service.BiletIndirimService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BiletIndirimServiceImpl implements BiletIndirimService {

    private final BiletIndirimRepository repository;

    public BiletIndirimServiceImpl(BiletIndirimRepository repository) {
        this.repository = repository;
    }

    @Override
    public BiletIndirim save(BiletIndirim b) {
        return repository.save(b);
    }

    @Override
    public void delete(BiletIndirimId id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<BiletIndirim> findById(BiletIndirimId id) {
        return repository.findById(id);
    }

    @Override
    public List<BiletIndirim> findAll() {
        return repository.findAll();
    }
}
