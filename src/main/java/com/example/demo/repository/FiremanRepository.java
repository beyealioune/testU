package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Fireman;


public interface FiremanRepository extends JpaRepository<Fireman, Long>  {
    
}
