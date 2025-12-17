package com.vtys.proje.service;

import com.vtys.proje.entity.KullaniciRol;
import com.vtys.proje.entity.KullaniciRolId;

import java.util.List;
import java.util.Optional;

public interface KullaniciRolService {

    KullaniciRol save(KullaniciRol k);

    KullaniciRol update(KullaniciRol k);

    void delete(KullaniciRolId id);

    Optional<KullaniciRol> findById(KullaniciRolId id);

    List<KullaniciRol> findAll();
}
