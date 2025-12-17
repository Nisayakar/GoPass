package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kullanici_rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KullaniciRol {

    @EmbeddedId
    private KullaniciRolId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("kullaniciId")
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rolId")
    @JoinColumn(name = "rol_id")
    private Rol rol;
}
