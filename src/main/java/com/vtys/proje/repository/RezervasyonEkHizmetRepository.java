package com.vtys.proje.repository;

import com.vtys.proje.entity.RezervasyonEkHizmet;
import com.vtys.proje.entity.RezervasyonEkHizmetId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RezervasyonEkHizmetRepository
        extends JpaRepository<RezervasyonEkHizmet, RezervasyonEkHizmetId> {
	List<RezervasyonEkHizmet> findByRezervasyon_RezervasyonId(Integer rezervasyonId);
}
