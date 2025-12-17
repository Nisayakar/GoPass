package com.vtys.proje.service;

import com.vtys.proje.entity.RotaPlan;
import com.vtys.proje.entity.RotaPlanId;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RotaPlanService {

    RotaPlan save(RotaPlan r);

    RotaPlan update(RotaPlan r);

    void delete(RotaPlanId id);

    Optional<RotaPlan> findById(RotaPlanId id);

    List<RotaPlan> findAll();

    // Yeni eklenen metod:
    List<RotaPlan> sefereGoreAra(String kalkis, String varis, LocalDate tarih);
}