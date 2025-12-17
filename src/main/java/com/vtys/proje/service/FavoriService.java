package com.vtys.proje.service;

import com.vtys.proje.entity.Favori;
import java.util.List;
import java.util.Optional;

public interface FavoriService {
    Favori save(Favori favori);
    Favori update(Favori favori);
    void delete(Integer id);
    Optional<Favori> findById(Integer id);
    List<Favori> findAll();
}
