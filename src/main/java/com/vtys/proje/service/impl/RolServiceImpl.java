package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Rol;
import com.vtys.proje.repository.RolRepository;
import com.vtys.proje.service.RolService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository repository;

    public RolServiceImpl(RolRepository repository) {
        this.repository = repository;
    }

    @Override
    public Rol save(Rol rol) {
        return repository.save(rol);
    }

    @Override
    public Rol update(Rol rol) {
        return repository.save(rol);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Rol> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Rol> findAll() {
        return repository.findAll();
    }
}
