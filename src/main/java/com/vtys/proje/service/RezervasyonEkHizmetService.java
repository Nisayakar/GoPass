package com.vtys.proje.service;

import com.vtys.proje.entity.RezervasyonEkHizmet;
import com.vtys.proje.entity.RezervasyonEkHizmetId;

import java.util.List;
import java.util.Optional;

public interface RezervasyonEkHizmetService {

    RezervasyonEkHizmet save(RezervasyonEkHizmet hizmet);

    void delete(RezervasyonEkHizmetId id);

    Optional<RezervasyonEkHizmet> findById(RezervasyonEkHizmetId id);

    List<RezervasyonEkHizmet> findAll();
}
