package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "rezervasyon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rezervasyon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rezervasyon_id")
    private Integer rezervasyonId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "koltuk_id")
    private Koltuk koltuk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yolcu_id")
    private Yolcu yolcu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rota_plan_id")
    private RotaPlan rotaPlan;

    @Column(name = "fiyat")
    private BigDecimal fiyat;

    @Column(name = "durum")
    private String durum;
}
