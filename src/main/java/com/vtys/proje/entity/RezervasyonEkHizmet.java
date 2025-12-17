package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rezervasyon_ek_hizmet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RezervasyonEkHizmet {

    @EmbeddedId
    private RezervasyonEkHizmetId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rezervasyonId")
    @JoinColumn(name = "rezervasyon_id")
    private Rezervasyon rezervasyon;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ekHizmetId")
    @JoinColumn(name = "ek_hizmet_id")
    private EkHizmet ekHizmet;
}
