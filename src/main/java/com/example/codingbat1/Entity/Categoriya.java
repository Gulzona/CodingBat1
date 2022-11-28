package com.example.codingbat1.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Categoriya {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nomi;
    @Column(nullable = false)
    private String izoh;
    @Column(nullable = false)
    private Integer yulduzchalarSoni;
    @JsonIgnore
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Tillar tillar;
}
