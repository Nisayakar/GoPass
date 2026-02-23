package com.vtys.proje.service.impl;

import com.vtys.proje.entity.RotaPlan;
import com.vtys.proje.repository.RotaPlanRepository;
import com.vtys.proje.service.RotaPlanService;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional; // Transaction eklemek iyidir

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RotaPlanServiceImpl implements RotaPlanService {

    private final RotaPlanRepository repository;

    public RotaPlanServiceImpl(RotaPlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public RotaPlan save(RotaPlan r) {
        return repository.save(r);
    }

    @Override
    public RotaPlan update(RotaPlan r) {
        return repository.save(r);
    }

    @Override
    public List<RotaPlan> findAll() {
        return repository.findAll();
    }


    @Override
    public List<RotaPlan> seferAra(
            String kalkis,
            String varis,
            String tip,
            LocalDate tarih
    ) {
        return repository.seferAra(kalkis, varis, tip, tarih);
    }

}
