package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "kullanici")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kullanici_id")
    private Integer kullaniciId;

    @Column(name = "isim", nullable = false, length = 100)
    private String isim;

    @Column(name = "soyisim", nullable = false, length = 100)
    private String soyisim;

    @Column(name = "eposta", unique = true, length = 150)
    private String eposta;

    @Column(name = "parola", length = 100)
    private String parola;

    // DÜZELTME: mappedBy "yolcu" sınıfındaki "kullanici" alanına bakmalı
    @OneToMany(mappedBy = "kullanici", cascade = CascadeType.ALL)
    @JsonIgnore 
    private List<Yolcu> yolcular;

    @OneToMany(mappedBy = "kullanici", cascade = CascadeType.ALL)
    @JsonIgnore 
    private List<Favori> favoriler;
}