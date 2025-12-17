package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "favori")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Favori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favori_id")
    private Integer favoriId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullanici_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Kullanici kullanici;

    // --- DEĞİŞEN KISIM: SEFER YERİNE ROTAPLAN ---
    // RotaPlan'ın ID'si 3 parçadan oluştuğu için burada 3 kolon ile bağlıyoruz
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "rota_id", referencedColumnName = "rota_id"),
        @JoinColumn(name = "arac_id", referencedColumnName = "arac_id"),
        @JoinColumn(name = "firma_id", referencedColumnName = "firma_id")
    })
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "firma", "arac"}) // Sonsuz döngü önlemleri
    private RotaPlan rotaPlan;
    // ---------------------------------------------

    @Column(name = "favori_tipi", length = 100)
    private String favoriTipi;

    @Column(name = "eklenme_tarihi")
    private LocalDate eklenmeTarihi;
}