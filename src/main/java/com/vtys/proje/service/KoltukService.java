package com.vtys.proje.service;

import com.vtys.proje.entity.Koltuk;
import java.util.List;
import java.util.Optional;

public interface KoltukService {
    Koltuk save(Koltuk koltuk);
    Koltuk update(Koltuk koltuk);
    void delete(Integer id);
    Optional<Koltuk> findById(Integer id);
    List<Koltuk> findAll();
}
