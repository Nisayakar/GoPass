package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(
    name = "rota_plan",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"arac_id", "sefer_tarihi", "sefer_saati"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RotaPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rota_plan_id")
    private Integer rotaPlanId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rota_id", nullable = false)
    private Rota rota;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "arac_id", nullable = false)
    private Arac arac;

    @Column(name = "sefer_tarihi", nullable = false)
    private LocalDate seferTarihi;

    @Column(name = "sefer_saati", nullable = false)
    private LocalTime seferSaati;

    @Column(name = "bilet_fiyati", nullable = false, precision = 10, scale = 2)
    private BigDecimal biletFiyati;
}
