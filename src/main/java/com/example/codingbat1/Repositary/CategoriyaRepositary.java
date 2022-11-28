package com.example.codingbat1.Repositary;

import com.example.codingbat1.Entity.Categoriya;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriyaRepositary extends JpaRepository<Categoriya,Integer> {
    @Override
    Optional<Categoriya> findById(Integer integer);
    List<Categoriya> findByTillarId(Integer tillar_id);

    boolean existsByNomiAndTillarId(String nomi, Integer tillar_id);

    boolean existsById(Integer id);
}
