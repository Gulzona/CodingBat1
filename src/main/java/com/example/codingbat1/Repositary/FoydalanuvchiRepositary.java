package com.example.codingbat1.Repositary;

import com.example.codingbat1.Entity.Foydalanuvchi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoydalanuvchiRepositary  extends JpaRepository<Foydalanuvchi,Integer> {
   boolean existsByEmailAndPassword(String email, String password);
}
