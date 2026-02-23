package com.vtys.proje.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firma_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "seferler"})
    private Firma firma;

    @Column(name = "arac_no", length = 50)
    private String aracNo;

    @Column(name = "kapasite")
    private Integer kapasite;

    @Column(name = "koltuk_duzeni", length = 100)
    private String koltukDuzeni;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ulasim_turu_id")
    private UlasimTuru ulasimTuru;

    // --- EKLENEN KISIM ---
    @OneToMany(mappedBy = "arac")
    @JsonIgnore
    private List<RotaPlan> seferler;
    
    @OneToMany(mappedBy = "arac")
    @JsonIgnore
    private List<Koltuk> koltuklar;
}