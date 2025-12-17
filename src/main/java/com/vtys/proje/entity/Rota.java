package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "rota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rota_id")
    private Integer rotaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kalkis_konum_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Konum kalkisKonum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "varis_konum_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Konum varisKonum;

    @Column(name = "km", precision = 8, scale = 2)
    private BigDecimal km;
}