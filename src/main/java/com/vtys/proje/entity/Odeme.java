package com.vtys.proje.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "odeme")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Odeme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "odeme_id")
    private Integer odemeId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE) 
    @JoinColumn(name = "rezervasyon_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Rezervasyon rezervasyon;

    @Column(name = "odeme_metodu", length = 50)
    private String odemeMetodu;

    @Column(name = "odeme_durumu", nullable = false, length = 50)
    private String odemeDurumu;

    @Column(name = "para_birimi", length = 20)
    private String paraBirimi;

    @Column(name = "fiyat", precision = 10, scale = 2)
    private BigDecimal fiyat;

    @Column(name = "iade_tutari", precision = 10, scale = 2)
    private BigDecimal iadeTutari;
}