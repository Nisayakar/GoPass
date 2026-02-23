package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
    name = "favori",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"kullanici_id", "rota_id"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favori_id")
    private Integer favoriId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firma_id", nullable = false)
    private Firma firma;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rota_id", nullable = false)
    private Rota rota;

    @Column(name = "eklenme_tarihi", nullable = false)
    private LocalDate eklenmeTarihi;
}
