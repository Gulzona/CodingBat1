package com.example.codingbat1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tillar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String tilNomi;

    @OneToMany(mappedBy = "tillar",cascade = CascadeType.ALL)
    private List<Categoriya> categoriyaList;



}
