package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "yolcu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Yolcu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "yolcu_id")
    private Integer yolcuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;

    @Column(name = "ad", length = 100) // SQL şemanızdaki Ad sütunu
    private String ad;

    @Column(name = "soyad", length = 100) // SQL şemanızdaki Soyad sütunu
    private String soyad;

    @Column(name = "yasi")
    private Integer yasi;

    @Column(name = "cinsiyet", length = 10)
    private String cinsiyet;

    // YENİ: Rezervasyon tablosundaki "yolcu" alanına bağlanır
    @OneToMany(mappedBy = "yolcu", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rezervasyon> rezervasyonlar;
}