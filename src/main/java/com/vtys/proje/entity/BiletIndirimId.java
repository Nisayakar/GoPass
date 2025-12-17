package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BiletIndirimId implements Serializable {

    @Column(name = "bilet_id")
    private Integer biletId;

    @Column(name = "indirim_id")
    private Integer indirimId;
}
