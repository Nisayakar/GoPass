package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "konum")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Konum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "konum_id")
    private Integer konumId;

    /**
     * Konumun görünen adı (İstanbul, Ankara Terminali vb.)
     */
    @Column(name = "konum_adi", nullable = false, length = 100)
    private String konumAdi;

    /**
     * Şehir bilgisi ayrı tabloda tutulur (normalizasyon).
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sehir_kodu", nullable = false)
    private Sehir sehir;

    // Kalkış yapılan rotalar
    @OneToMany(mappedBy = "kalkisKonum")
    @JsonIgnore
    private List<Rota> kalkisRotalari;

    // Varış yapılan rotalar
    @OneToMany(mappedBy = "varisKonum")
    @JsonIgnore
    private List<Rota> varisRotalari;
}
