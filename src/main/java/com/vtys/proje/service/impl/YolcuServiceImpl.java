package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Yolcu;
import com.vtys.proje.repository.YolcuRepository;
import com.vtys.proje.service.YolcuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class YolcuServiceImpl implements YolcuService {

    private final YolcuRepository repository;

    public YolcuServiceImpl(YolcuRepository repository) {
        this.repository = repository;
    }

    public Yolcu save(Yolcu y) { return repository.save(y); }
    public Yolcu update(Yolcu y) { return repository.save(y); }
    public void delete(Integer id) { repository.deleteById(id); }
    public Optional<Yolcu> findById(Integer id) { return repository.findById(id); }
    public List<Yolcu> findAll() { return repository.findAll(); }
}
