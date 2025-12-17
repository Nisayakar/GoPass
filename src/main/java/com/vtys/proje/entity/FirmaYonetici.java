package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "firma_yonetici")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FirmaYonetici {

    @EmbeddedId
    private FirmaYoneticiId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("kullaniciId")
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("firmaId")
    @JoinColumn(name = "firma_id")
    private Firma firma;
}
