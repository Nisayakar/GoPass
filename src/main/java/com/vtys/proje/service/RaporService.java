package com.vtys.proje.service;

import com.vtys.proje.entity.Rapor;
import java.util.List;
import java.util.Optional;

public interface RaporService {
    Rapor save(Rapor rapor);
    Rapor update(Rapor rapor);
    void delete(Integer id);
    Optional<Rapor> findById(Integer id);
    List<Rapor> findAll();
}
