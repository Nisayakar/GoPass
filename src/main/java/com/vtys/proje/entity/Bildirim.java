package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "bildirim")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bildirim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bildirim_id")
    private Integer bildirimId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;

    @Column(name = "mesaj")
    private String mesaj;

    @Column(name = "tarih")
    private LocalDate tarih;

    @Column(name = "gosterim_durumu", length = 50)
    private String gosterimDurumu;

    @Column(name = "tur", length = 50)
    private String tur;
}
