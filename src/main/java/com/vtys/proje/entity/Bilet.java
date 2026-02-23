package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "bilet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bilet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bilet_id")
    private Integer biletId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rezervasyon_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "kullanici"})
    private Rezervasyon rezervasyon;

    @Column(name = "olusturulma_tarihi")
    private LocalDate olusturulmaTarihi;

    @Column(name = "qr_kod", length = 255)
    private String qrKod;

    @Column(name = "bilet_no", length = 50)
    private String biletNo;
}
