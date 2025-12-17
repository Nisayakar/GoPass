package com.vtys.proje.service;

import com.vtys.proje.entity.Fatura;
import java.util.List;
import java.util.Optional;

public interface FaturaService {
    Fatura save(Fatura fatura);
    Fatura update(Fatura fatura);
    void delete(Integer id);
    Optional<Fatura> findById(Integer id);
    List<Fatura> findAll();
}
