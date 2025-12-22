package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "konum")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Konum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "konum_id")
    private Integer konumId;

    @Column(name = "sehir", nullable = false, length = 100)
    private String sehir;

    @Column(name = "sehir_kodu")
    private Integer sehirKodu;

    
    @OneToMany(mappedBy = "kalkisKonum")
    @JsonIgnore
    private List<Rota> kalkisRotalari;

    @OneToMany(mappedBy = "varisKonum")
    @JsonIgnore
    private List<Rota> varisRotalari;
}