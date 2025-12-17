package com.vtys.proje.service;

import com.vtys.proje.entity.Firma;
import java.util.List;
import java.util.Optional;

public interface FirmaService {
    Firma save(Firma firma);
    Firma update(Firma firma);
    void delete(Integer id);
    Optional<Firma> findById(Integer id);
    List<Firma> findAll();
}
