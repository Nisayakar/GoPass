package com.vtys.proje.service.impl;

import com.vtys.proje.entity.KullaniciRol;
import com.vtys.proje.entity.KullaniciRolId;
import com.vtys.proje.repository.KullaniciRolRepository;
import com.vtys.proje.service.KullaniciRolService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KullaniciRolServiceImpl implements KullaniciRolService {

    private final KullaniciRolRepository repository;

    public KullaniciRolServiceImpl(KullaniciRolRepository repository) {
        this.repository = repository;
    }

    @Override
    public KullaniciRol save(KullaniciRol k) {
        return repository.save(k);
    }

    @Override
    public KullaniciRol update(KullaniciRol k) {
        return repository.save(k);
    }

    @Override
    public void delete(KullaniciRolId id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<KullaniciRol> findById(KullaniciRolId id) {
        return repository.findById(id);
    }

    @Override
    public List<KullaniciRol> findAll() {
        return repository.findAll();
    }
}
