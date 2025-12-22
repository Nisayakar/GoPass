package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Kullanici;
import com.vtys.proje.repository.KullaniciRepository;
import com.vtys.proje.service.KullaniciService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KullaniciServiceImpl implements KullaniciService {

    private final KullaniciRepository repository;

    public KullaniciServiceImpl(KullaniciRepository repository) {
        this.repository = repository;
    }

    @Override
    public Kullanici save(Kullanici k) {
        return repository.save(k);
    }

    @Override
    public Kullanici update(Kullanici k) {
        return repository.save(k);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Kullanici> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Kullanici> findAll() {
        return repository.findAll();
    }

    
    @Override
    public Optional<Kullanici> login(String eposta, String parola) {
        return repository.findByEposta(eposta)
                .filter(k -> k.getParola().equals(parola));
    }
}
