package com.vtys.proje.service;

import com.vtys.proje.entity.Odeme;
import java.util.List;
import java.util.Optional;

public interface OdemeService {
    Odeme save(Odeme odeme);
    Odeme update(Odeme odeme);
    void delete(Integer id);
    Optional<Odeme> findById(Integer id);
    List<Odeme> findAll();
}
