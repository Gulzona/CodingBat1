package com.example.codingbat1.Repositary;

import com.example.codingbat1.Entity.Savollar;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface    SavollarRepositary extends JpaRepository<Savollar,Integer> {
  boolean existsByNomiAndCategoriya_Id(String nomi, Integer categoriya_id);
}
