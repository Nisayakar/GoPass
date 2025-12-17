package com.vtys.proje.service;

import com.vtys.proje.entity.BiletIndirim;
import com.vtys.proje.entity.BiletIndirimId;

import java.util.List;
import java.util.Optional;

public interface BiletIndirimService {

    BiletIndirim save(BiletIndirim biletIndirim);

    void delete(BiletIndirimId id);

    Optional<BiletIndirim> findById(BiletIndirimId id);

    List<BiletIndirim> findAll();
}
