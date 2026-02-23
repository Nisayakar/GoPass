package com.vtys.proje.service;

import com.vtys.proje.entity.Koltuk;
import java.util.List;

public interface KoltukService {

    List<Koltuk> getKoltuklarByRotaPlanId(Integer rotaPlanId);
}
