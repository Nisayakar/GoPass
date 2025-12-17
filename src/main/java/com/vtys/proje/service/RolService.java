package com.vtys.proje.service;

import com.vtys.proje.entity.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {

    Rol save(Rol rol);

    Rol update(Rol rol);

    void delete(Integer id);

    Optional<Rol> findById(Integer id);

    List<Rol> findAll();
}
