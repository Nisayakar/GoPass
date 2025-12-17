package com.vtys.proje.repository;

import com.vtys.proje.entity.Koltuk;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KoltukRepository extends JpaRepository<Koltuk, Integer> {

    List<Koltuk> findByArac_AracIdOrderByKoltukNoAsc(Integer aracId);
}