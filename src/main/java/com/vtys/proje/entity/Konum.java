package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "konum")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Konum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "konum_id")
    private Integer konumId;

    @Column(name = "sehir", nullable = false, length = 100)
    private String sehir;

    @Column(name = "sehir_kodu")
    private Integer sehirKodu;
}
