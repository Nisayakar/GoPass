package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Indirim;
import com.vtys.proje.repository.IndirimRepository;
import com.vtys.proje.service.IndirimService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndirimServiceImpl implements IndirimService {

    private final IndirimRepository repository;

    public IndirimServiceImpl(IndirimRepository repository) {
        this.repository = repository;
    }

    public Indirim save(Indirim i) { return repository.save(i); }
    public Indirim update(Indirim i) { return repository.save(i); }
    public void delete(Integer id) { repository.deleteById(id); }
    public Optional<Indirim> findById(Integer id) { return repository.findById(id); }
    public List<Indirim> findAll() { return repository.findAll(); }
}
