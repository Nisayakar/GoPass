package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "koltuk")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Koltuk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "koltuk_id")
    private Integer koltukId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arac_id")
    @JsonIgnore // Koltuk bilgisini çekerken tüm otobüsü getirme
    private Arac arac;

    @Column(name = "koltuk_turu", length = 50)
    private String koltukTuru; // Cam Kenarı, Koridor vb.

    @Column(name = "durum", length = 50)
    private String durum; // Boş, Dolu, Rezerve

    @Column(name = "koltuk_no", length = 20)
    private String koltukNo;
}