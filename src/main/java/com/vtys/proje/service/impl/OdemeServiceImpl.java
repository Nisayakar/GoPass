package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Odeme;
import com.vtys.proje.repository.OdemeRepository;
import com.vtys.proje.service.OdemeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdemeServiceImpl implements OdemeService {

    private final OdemeRepository repository;

    public OdemeServiceImpl(OdemeRepository repository) {
        this.repository = repository;
    }

    public Odeme save(Odeme o) { return repository.save(o); }
    public Odeme update(Odeme o) { return repository.save(o); }
    public void delete(Integer id) { repository.deleteById(id); }
    public Optional<Odeme> findById(Integer id) { return repository.findById(id); }
    public List<Odeme> findAll() { return repository.findAll(); }
}
