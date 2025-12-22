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

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rotaId")
    @JoinColumn(name = "rota_id")

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "planlar"}) 
    private Rota rota;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("aracId")
    @JoinColumn(name = "arac_id")
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "seferler", "koltuklar"})
    private Arac arac;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("firmaId")
    @JoinColumn(name = "firma_id")

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "seferler"})
    private Firma firma;

    @Column(name = "sefer_tarihi")
    private LocalDate seferTarihi;

    @Column(name = "sefer_saati")
    private LocalTime seferSaati;
    
 
    @Column(name = "varis_saati")
    private LocalTime varisSaati;

    @Column(name = "bilet_fiyati")
    private BigDecimal biletFiyati;

    @Column(name = "tahmini_sure")
    private String tahminiSure;
}