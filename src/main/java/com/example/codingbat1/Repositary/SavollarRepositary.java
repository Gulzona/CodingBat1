package com.example.codingbat1.Repositary;

import com.example.codingbat1.Entity.Savollar;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface    SavollarRepositary extends JpaRepository<Savollar,Integer> {
  boolean existsByNomiAndTillarId(String nomi, Integer tillar_id);
}
