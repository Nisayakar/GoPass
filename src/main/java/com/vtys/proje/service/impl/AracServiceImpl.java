package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Arac;
import com.vtys.proje.repository.AracRepository;
import com.vtys.proje.service.AracService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AracServiceImpl implements AracService {

    private final AracRepository repository;

    public AracServiceImpl(AracRepository repository) {
        this.repository = repository;
    }

    public Arac save(Arac a) {
        return repository.save(a);
    }

    public Arac update(Arac a) {
        return repository.save(a);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Arac> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Arac> findAll() {
        return repository.findAll();
    }
}
