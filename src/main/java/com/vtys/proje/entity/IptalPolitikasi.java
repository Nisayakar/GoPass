package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "iptal_politikasi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IptalPolitikasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iptal_politikasi_id")
    private Integer iptalPolitikasiId;

    @Column(name = "politika", nullable = false)
    private String politika;

    @Column(name = "fiyat", precision = 5, scale = 2)
    private BigDecimal fiyat;

    @Column(name = "durum", length = 50, nullable = false)
    private String durum;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "firma_id", nullable = false)
    private Firma firma;
}
