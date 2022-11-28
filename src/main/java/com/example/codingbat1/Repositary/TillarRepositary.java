package com.example.codingbat1.Repositary;

import com.example.codingbat1.Entity.Tillar;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TillarRepositary extends JpaRepository<Tillar, Integer> {
    boolean existsByTilNomi(String tilNomi);


}
