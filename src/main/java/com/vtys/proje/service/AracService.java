package com.vtys.proje.service;

import com.vtys.proje.entity.Arac;
import java.util.List;
import java.util.Optional;

public interface AracService {
    Arac save(Arac arac);
    Arac update(Arac arac);
    void delete(Integer id);
    Optional<Arac> findById(Integer id);
    List<Arac> findAll();
}
