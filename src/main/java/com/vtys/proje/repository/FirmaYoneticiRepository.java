package com.vtys.proje.repository;

import com.vtys.proje.entity.FirmaYonetici;
import com.vtys.proje.entity.FirmaYoneticiId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmaYoneticiRepository
        extends JpaRepository<FirmaYonetici, FirmaYoneticiId> {
}
