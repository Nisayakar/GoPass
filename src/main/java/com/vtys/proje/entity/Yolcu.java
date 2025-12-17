package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "yasi")
    private Integer yasi;

    @Column(name = "cinsiyet", length = 10)
    private String cinsiyet;
}
