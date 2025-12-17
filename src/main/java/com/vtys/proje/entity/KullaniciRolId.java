package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KullaniciRolId implements Serializable {

    @Column(name = "kullanici_id")
    private Integer kullaniciId;

    @Column(name = "rol_id")
    private Integer rolId;
}
