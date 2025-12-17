package com.vtys.proje.service;

import com.vtys.proje.entity.EkHizmet;
import java.util.List;
import java.util.Optional;

public interface EkHizmetService {
    EkHizmet save(EkHizmet hizmet);
    EkHizmet update(EkHizmet hizmet);
    void delete(Integer id);
    Optional<EkHizmet> findById(Integer id);
    List<EkHizmet> findAll();
}
