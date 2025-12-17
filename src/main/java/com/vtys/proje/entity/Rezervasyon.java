package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(
    name = "rezervasyon",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"koltuk_id"}) // Bir koltuk sadece bir rezervasyonda (aktifken) olabilir
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Rezervasyon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rezervasyon_id")
    private Integer rezervasyonId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firma_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "rezervasyonlar", "seferler"}) 
    private Firma firma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "koltuk_id")
    // Koltuğu getir ama aracın detayına girip sistemi yorma
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "arac"})
    private Koltuk koltuk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullanici_id")
    // Sadece kayıt yaparken (POST) kullanıcı ID'si al, okurken (GET) kullanıcıyı getirme (Döngü önlemi)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Kullanici kullanici;

    @Column(name = "fiyat", precision = 10, scale = 2)
    private BigDecimal fiyat;

    @Column(name = "durum", length = 50)
    private String durum; // Örn: "Rezerve", "Biletlendi", "Iptal"
}