package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ek_hizmet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EkHizmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ek_hizmet_id")
    private Integer ekHizmetId;

    @Column(name = "ad", length = 100)
    private String ad;

    @Column(name = "fiyat", precision = 10, scale = 2)
    private BigDecimal fiyat;

    @Column(name = "aciklama")
    private String aciklama;
}
