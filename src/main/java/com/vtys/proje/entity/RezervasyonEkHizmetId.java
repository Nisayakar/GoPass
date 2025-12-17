package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RezervasyonEkHizmetId implements Serializable {

    @Column(name = "rezervasyon_id")
    private Integer rezervasyonId;

    @Column(name = "ek_hizmet_id")
    private Integer ekHizmetId;
}
