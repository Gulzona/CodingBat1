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
public class Savollar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true)
    private String nomi;

    @Column(nullable = false)
    private String matni;

    @Column(nullable = false)
    private String yechim;

    @Column(nullable = false)
    private String funksiya;

    @Column(nullable = false)
    private String yordam;



    @OneToOne()
    private Namuna namuna;

    @OneToOne(fetch = FetchType.LAZY)
    private Tillar tillar;

//    @JsonIgnore
    @OneToOne()
    private Categoriya categoriya;
//    optional = false,fetch = FetchType.LAZY

}
