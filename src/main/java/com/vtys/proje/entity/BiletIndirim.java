package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bilet_indirim")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BiletIndirim {

    @EmbeddedId
    private BiletIndirimId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("biletId")
    @JoinColumn(name = "bilet_id")
    private Bilet bilet;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("indirimId")
    @JoinColumn(name = "indirim_id")
    private Indirim indirim;
}
