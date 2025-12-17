package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Konum;
import com.vtys.proje.repository.KonumRepository;
import com.vtys.proje.service.KonumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KonumServiceImpl implements KonumService {

    private final KonumRepository repository;

    public KonumServiceImpl(KonumRepository repository) {
        this.repository = repository;
    }

    public Konum save(Konum k) { return repository.save(k); }
    public Konum update(Konum k) { return repository.save(k); }
    public void delete(Integer id) { repository.deleteById(id); }
    public Optional<Konum> findById(Integer id) { return repository.findById(id); }
    public List<Konum> findAll() { return repository.findAll(); }
}
