package com.vtys.proje.service;

import com.vtys.proje.entity.Yolcu;
import java.util.List;
import java.util.Optional;

public interface YolcuService {
    Yolcu save(Yolcu yolcu);
    Yolcu update(Yolcu yolcu);
    void delete(Integer id);
    Optional<Yolcu> findById(Integer id);
    List<Yolcu> findAll();
}
