package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Koltuk;
import com.vtys.proje.repository.KoltukRepository;
import com.vtys.proje.service.KoltukService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KoltukServiceImpl implements KoltukService {

    private final KoltukRepository repository;

    public KoltukServiceImpl(KoltukRepository repository) {
        this.repository = repository;
    }

    public Koltuk save(Koltuk k) {
        return repository.save(k);
    }

    public Koltuk update(Koltuk k) {
        return repository.save(k);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Koltuk> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Koltuk> findAll() {
        return repository.findAll();
    }
}
