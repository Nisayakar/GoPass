package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "rota_plan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RotaPlan {

    @EmbeddedId
    private RotaPlanId id;

    // ... Mevcut ilişkiler (Rota, Arac, Firma) burada kalsın ...
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rotaId")
    @JoinColumn(name = "rota_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Rota rota;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("aracId")
    @JoinColumn(name = "arac_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Arac arac;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("firmaId")
    @JoinColumn(name = "firma_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Firma firma;

    // --- EKLENEN ALANLAR ---
    @Column(name = "sefer_tarihi")
    private LocalDate seferTarihi;

    @Column(name = "sefer_saati")
    private LocalTime seferSaati;

    @Column(name = "bilet_fiyati")
    private BigDecimal biletFiyati;

    @Column(name = "tahmini_sure")
    private String tahminiSure;
}