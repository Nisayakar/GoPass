package com.vtys.proje.service;

import com.vtys.proje.entity.Kampanya;
import java.util.List;
import java.util.Optional;

public interface KampanyaService {
    Kampanya save(Kampanya kampanya);
    Kampanya update(Kampanya kampanya);
    void delete(Integer id);
    Optional<Kampanya> findById(Integer id);
    List<Kampanya> findAll();
}
