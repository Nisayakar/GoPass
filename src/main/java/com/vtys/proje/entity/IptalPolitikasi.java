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

    @Column(name = "politika")
    private String politika;

    @Column(name = "fiyat", precision = 10, scale = 2)
    private BigDecimal fiyat;

    @Column(name = "durum", length = 50)
    private String durum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arac_id")
    private Arac arac;
}
