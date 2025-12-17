package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FirmaYoneticiId implements Serializable {

    @Column(name = "kullanici_id")
    private Integer kullaniciId;

    @Column(name = "firma_id")
    private Integer firmaId;
}
