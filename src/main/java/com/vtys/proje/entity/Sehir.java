package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sehir")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sehir {

    @Id
    @Column(name = "sehir_kodu")
    private Integer sehirKodu;

    @Column(name = "sehir_adi", nullable = false, length = 100)
    private String sehirAdi;
}
