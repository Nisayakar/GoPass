package com.vtys.proje.service;

import com.vtys.proje.entity.FirmaYonetici;
import com.vtys.proje.entity.FirmaYoneticiId;

import java.util.List;
import java.util.Optional;

public interface FirmaYoneticiService {

    FirmaYonetici save(FirmaYonetici firmaYonetici);

    void delete(FirmaYoneticiId id);

    Optional<FirmaYonetici> findById(FirmaYoneticiId id);

    List<FirmaYonetici> findAll();
}
