package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Fire;

public interface FireRepository extends JpaRepository<Fire, Long>{
    
}
