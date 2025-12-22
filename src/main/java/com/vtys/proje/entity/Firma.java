package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "firma")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Firma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "firma_id")
    private Integer firmaId;

    @Column(name = "firma_adi", nullable = false, length = 150)
    private String firmaAdi;

    @Column(name = "firma_telno", length = 20)
    private String firmaTelNo;

    @Column(name = "firma_email", length = 150, unique = true)
    private String firmaEmail;

    @Column(name = "islem_durumu", length = 50)
    private String islemDurumu;

    @OneToMany(mappedBy = "firma")
    @JsonIgnore 
    private List<RotaPlan> seferler;

    @OneToMany(mappedBy = "firma")
    @JsonIgnore 
    private List<Rezervasyon> rezervasyonlar;
}