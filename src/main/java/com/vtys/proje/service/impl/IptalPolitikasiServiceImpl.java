package com.vtys.proje.service.impl;

import com.vtys.proje.entity.IptalPolitikasi;
import com.vtys.proje.repository.IptalPolitikasiRepository;
import com.vtys.proje.service.IptalPolitikasiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IptalPolitikasiServiceImpl implements IptalPolitikasiService {

    private final IptalPolitikasiRepository repository;

    public IptalPolitikasiServiceImpl(IptalPolitikasiRepository repository) {
        this.repository = repository;
    }

    public IptalPolitikasi save(IptalPolitikasi i) { return repository.save(i); }
    public IptalPolitikasi update(IptalPolitikasi i) { return repository.save(i); }
    public void delete(Integer id) { repository.deleteById(id); }
    public Optional<IptalPolitikasi> findById(Integer id) { return repository.findById(id); }
    public List<IptalPolitikasi> findAll() { return repository.findAll(); }
}
