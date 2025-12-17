package com.vtys.proje.service;

import com.vtys.proje.entity.IptalPolitikasi;
import java.util.List;
import java.util.Optional;

public interface IptalPolitikasiService {
    IptalPolitikasi save(IptalPolitikasi politika);
    IptalPolitikasi update(IptalPolitikasi politika);
    void delete(Integer id);
    Optional<IptalPolitikasi> findById(Integer id);
    List<IptalPolitikasi> findAll();
}
