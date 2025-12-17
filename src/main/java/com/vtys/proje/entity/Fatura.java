package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fatura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fatura_id")
    private Integer faturaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odeme_id")
    private Odeme odeme;

    @Column(name = "tarih")
    private LocalDate tarih;

    @Column(name = "tutar", precision = 10, scale = 2)
    private BigDecimal tutar;
}
