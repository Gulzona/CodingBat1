package com.example.codingbat1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Javoblar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private boolean tekshirish;

    @OneToOne
    private Savollar savollar;

    @ManyToOne
    private Foydalanuvchi foydalanuvchi;

}
