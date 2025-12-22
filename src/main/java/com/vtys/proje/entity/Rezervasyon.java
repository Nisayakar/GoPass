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
        // DÜZELTME: Bir koltuk, AYNI SEFERDE (rota+arac+firma) birden fazla satılamaz.
        // Ama farklı seferlerde tekrar satılabilir.
        @UniqueConstraint(columnNames = {"koltuk_id", "rota_id", "arac_id", "firma_id"}) 
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

    // --- EKLENEN KISIM BAŞLANGIÇ ---
    // Rezervasyonun hangi sefere ait olduğunu belirtmek zorundayız.
    // RotaPlan ID'si 3 parçadan (Composite) oluştuğu için @JoinColumns kullanıyoruz.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "rota_id", referencedColumnName = "rota_id"),
        @JoinColumn(name = "arac_id", referencedColumnName = "arac_id"),
        @JoinColumn(name = "firma_id", referencedColumnName = "firma_id")
    })
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "rezervasyonlar"})
    private RotaPlan rotaPlan;
    // --- EKLENEN KISIM BİTİŞ ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firma_id", insertable = false, updatable = false) 
    // Not: Firma bilgisi zaten RotaPlan içinde var, burada tekrar insert etmeye gerek yok ama 
    // ilişki kalabilir (insertable=false yaptık ki çakışma olmasın).
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "rezervasyonlar", "seferler"}) 
    private Firma firma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "koltuk_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "arac"})
    private Koltuk koltuk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullanici_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Kullanici kullanici;

    @Column(name = "fiyat", precision = 10, scale = 2)
    private BigDecimal fiyat;

    @Column(name = "durum", length = 50)
    private String durum; 
}