package com.vtys.proje.service.impl;

import com.vtys.proje.entity.Koltuk;
import com.vtys.proje.entity.RotaPlan;
import com.vtys.proje.repository.KoltukRepository;
import com.vtys.proje.repository.RotaPlanRepository;
import com.vtys.proje.service.KoltukService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KoltukServiceImpl implements KoltukService {

    private final KoltukRepository koltukRepository;
    private final RotaPlanRepository rotaPlanRepository;

    public KoltukServiceImpl(KoltukRepository koltukRepository,
                             RotaPlanRepository rotaPlanRepository) {
        this.koltukRepository = koltukRepository;
        this.rotaPlanRepository = rotaPlanRepository;
    }

    @Override
    public List<Koltuk> getKoltuklarByRotaPlanId(Integer rotaPlanId) {

        RotaPlan rotaPlan = rotaPlanRepository.findById(rotaPlanId)
                .orElseThrow(() -> new RuntimeException("Sefer bulunamadı"));

        Integer aracId = rotaPlan.getArac().getAracId();

        return koltukRepository
                .findByArac_AracIdOrderByKoltukNoAsc(aracId);
    }
}
