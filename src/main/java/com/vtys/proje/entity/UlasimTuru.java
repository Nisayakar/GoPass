package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ulasim_turu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UlasimTuru {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ulasim_turu_id")
    private Integer ulasimTuruId;

    @Column(name = "aciklama")
    private String aciklama;

    @Column(name = "koltuk_tipi", length = 50)
    private String koltukTipi;

    @Column(name = "arac_tipi", length = 50)
    private String aracTipi;
}
