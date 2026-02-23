package com.vtys.proje.service;

import com.vtys.proje.entity.RotaPlan;
import java.time.LocalDate;
import java.util.List;

public interface RotaPlanService {

    RotaPlan save(RotaPlan r);

    RotaPlan update(RotaPlan r);

    List<RotaPlan> findAll();


    List<RotaPlan> seferAra(
        String kalkis,
        String varis,
        String tip,       
        LocalDate tarih    
    );
}
