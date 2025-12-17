package com.vtys.proje.service;

import com.vtys.proje.entity.UlasimTuru;
import java.util.List;
import java.util.Optional;

public interface UlasimTuruService {
    UlasimTuru save(UlasimTuru ulasimTuru);
    UlasimTuru update(UlasimTuru ulasimTuru);
    void delete(Integer id);
    Optional<UlasimTuru> findById(Integer id);
    List<UlasimTuru> findAll();
}
