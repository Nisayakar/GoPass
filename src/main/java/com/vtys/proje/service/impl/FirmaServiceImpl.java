package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Firma;
import com.vtys.proje.repository.FirmaRepository;
import com.vtys.proje.service.FirmaService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FirmaServiceImpl implements FirmaService {

    private final FirmaRepository firmaRepository;

    public FirmaServiceImpl(FirmaRepository firmaRepository) {
        this.firmaRepository = firmaRepository;
    }

    public Firma save(Firma firma) {
        return firmaRepository.save(firma);
    }

    public Firma update(Firma firma) {
        return firmaRepository.save(firma);
    }

    public void delete(Integer id) {
        firmaRepository.deleteById(id);
    }

    public Optional<Firma> findById(Integer id) {
        return firmaRepository.findById(id);
    }

    public List<Firma> findAll() {
        return firmaRepository.findAll();
    }
}
