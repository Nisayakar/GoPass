package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "rapor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rapor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rapor_id")
    private Integer raporId;

    @ManyToOne
    @JoinColumn(name = "firma_id")
    private Firma firma;

    @Column(name = "rapor_tipi")
    private String raporTipi;

    @Column(name = "rapor_veri")
    private String raporVeri;

    @Column(name = "tarih")
    private LocalDate tarih;
}
