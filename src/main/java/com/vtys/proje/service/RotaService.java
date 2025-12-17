package com.vtys.proje.service;

import com.vtys.proje.entity.Rota;
import java.util.List;
import java.util.Optional;

public interface RotaService {
    Rota save(Rota rota);
    Rota update(Rota rota);
    void delete(Integer id);
    Optional<Rota> findById(Integer id);
    List<Rota> findAll();
}
