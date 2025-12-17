package com.vtys.proje.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "arac")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Arac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arac_id")
    private Integer aracId;

    // Firma_ID (FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firma_id")
    private Firma firma;

    @Column(name = "arac_tipi", length = 100)
    private String aracTipi;

    @Column(name = "arac_no", length = 50)
    private String aracNo;

    @Column(name = "kapasite")
    private Integer kapasite;

    @Column(name = "koltuk_duzeni", length = 100)
    private String koltukDuzeni;

    // Ulasim_Turu_ID (FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ulasim_turu_id")
    private UlasimTuru ulasimTuru;
}
