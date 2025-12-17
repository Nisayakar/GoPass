package com.vtys.proje.repository;

import com.vtys.proje.entity.RezervasyonEkHizmet;
import com.vtys.proje.entity.RezervasyonEkHizmetId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RezervasyonEkHizmetRepository
        extends JpaRepository<RezervasyonEkHizmet, RezervasyonEkHizmetId> {
}
