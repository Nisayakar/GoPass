package com.vtys.proje.service.impl;

import com.vtys.proje.entity.EkHizmet;
import com.vtys.proje.repository.EkHizmetRepository;
import com.vtys.proje.service.EkHizmetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EkHizmetServiceImpl implements EkHizmetService {

    private final EkHizmetRepository repository;

    public EkHizmetServiceImpl(EkHizmetRepository repository) {
        this.repository = repository;
    }

    public EkHizmet save(EkHizmet e) { return repository.save(e); }
    public EkHizmet update(EkHizmet e) { return repository.save(e); }
    public void delete(Integer id) { repository.deleteById(id); }
    public Optional<EkHizmet> findById(Integer id) { return repository.findById(id); }
    public List<EkHizmet> findAll() { return repository.findAll(); }
}
