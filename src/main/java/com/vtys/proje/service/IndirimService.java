package com.vtys.proje.service;

import com.vtys.proje.entity.Indirim;
import java.util.List;
import java.util.Optional;

public interface IndirimService {
    Indirim save(Indirim indirim);
    Indirim update(Indirim indirim);
    void delete(Integer id);
    Optional<Indirim> findById(Integer id);
    List<Indirim> findAll();
}
