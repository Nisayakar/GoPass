package com.vtys.proje.repository;

import com.vtys.proje.entity.BiletIndirim;
import com.vtys.proje.entity.BiletIndirimId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiletIndirimRepository
        extends JpaRepository<BiletIndirim, BiletIndirimId> {
}
