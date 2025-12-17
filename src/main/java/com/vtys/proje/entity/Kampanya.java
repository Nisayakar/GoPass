package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "kampanya")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Kampanya {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kampanya_id")
    private Integer kampanyaId;

    @Column(name = "ad", length = 100)
    private String ad;

    @Column(name = "indirim_orani", precision = 5, scale = 2)
    private BigDecimal indirimOrani;

    @Column(name = "baslangic")
    private LocalDate baslangic;

    @Column(name = "bitis")
    private LocalDate bitis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firma_id")
    private Firma firma;
}
