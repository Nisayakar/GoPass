package com.vtys.proje.service;

import com.vtys.proje.entity.Bilet;
import java.util.List;
import java.util.Optional;

public interface BiletService {
    Bilet save(Bilet bilet);
    Bilet update(Bilet bilet);
    void delete(Integer id);
    Optional<Bilet> findById(Integer id);
    List<Bilet> findAll();
}
