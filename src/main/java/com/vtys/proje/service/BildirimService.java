package com.vtys.proje.service;

import com.vtys.proje.entity.Bildirim;
import java.util.List;
import java.util.Optional;

public interface BildirimService {
    Bildirim save(Bildirim bildirim);
    Bildirim update(Bildirim bildirim);
    void delete(Integer id);
    Optional<Bildirim> findById(Integer id);
    List<Bildirim> findAll();
}
