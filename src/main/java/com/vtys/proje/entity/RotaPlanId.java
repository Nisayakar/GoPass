package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode 
public class RotaPlanId implements Serializable {

    @Column(name = "rota_id")
    private Integer rotaId;

    @Column(name = "arac_id")
    private Integer aracId;

    @Column(name = "firma_id")
    private Integer firmaId;
}