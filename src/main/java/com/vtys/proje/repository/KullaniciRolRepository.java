package com.vtys.proje.repository;

import com.vtys.proje.entity.KullaniciRol;
import com.vtys.proje.entity.KullaniciRolId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KullaniciRolRepository
        extends JpaRepository<KullaniciRol, KullaniciRolId> {
}
