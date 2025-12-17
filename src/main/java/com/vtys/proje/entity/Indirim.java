package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "indirim")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Indirim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "indirim_id")
    private Integer indirimId;

    @Column(name = "kod", length = 50)
    private String kod;

    @Column(name = "indirim_degeri", precision = 5, scale = 2)
    private BigDecimal indirimDegeri;

    @Column(name = "durum", length = 50)
    private String durum;

    @Column(name = "kullanim_hakki", length = 50)
    private String kullanimHakki;

    @Column(name = "indirim_araligi", length = 50)
    private String indirimAraligi;
}
