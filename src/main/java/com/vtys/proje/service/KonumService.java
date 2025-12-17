package com.vtys.proje.service;

import com.vtys.proje.entity.Konum;
import java.util.List;
import java.util.Optional;

public interface KonumService {
    Konum save(Konum konum);
    Konum update(Konum konum);
    void delete(Integer id);
    Optional<Konum> findById(Integer id);
    List<Konum> findAll();
}
