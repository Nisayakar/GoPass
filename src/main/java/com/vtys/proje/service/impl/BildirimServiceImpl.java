package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Bildirim;
import com.vtys.proje.repository.BildirimRepository;
import com.vtys.proje.service.BildirimService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BildirimServiceImpl implements BildirimService {

    private final BildirimRepository repository;

    public BildirimServiceImpl(BildirimRepository repository) {
        this.repository = repository;
    }

    public Bildirim save(Bildirim b) { return repository.save(b); }
    public Bildirim update(Bildirim b) { return repository.save(b); }
    public void delete(Integer id) { repository.deleteById(id); }
    public Optional<Bildirim> findById(Integer id) { return repository.findById(id); }
    public List<Bildirim> findAll() { return repository.findAll(); }
}
