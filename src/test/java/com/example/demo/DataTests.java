package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Fire;
import com.example.demo.model.Fireman;
import com.example.demo.repository.FireRepository;
import com.example.demo.repository.FiremanRepository;

@DataJpaTest
 public class DataTests {


    @Autowired
    FireRepository fireRepository;

    @Autowired
    FiremanRepository firemanRepository;




    @Test
    public void testCreateFireAndFireman() {
        int severity = 8;
        Instant date = Instant.now();
    
        // Création des feux
        Fire fire1 = new Fire(severity, date);
        Fire fire2 = new Fire(severity, date);
    
        // Sauvegarde des feux dans la base de données
        fireRepository.save(fire1);
        fireRepository.save(fire2);
    
        // Création du pompier
        Fireman fireman = new Fireman();
        fireman.setName("John Doe");
    
        // Association du pompier aux feux
        fireman.setExtinguishedFires(Arrays.asList(fire1, fire2));
    
        // Sauvegarde du pompier dans la base de données
        firemanRepository.save(fireman);
    
        // Vérification des associations
        Optional<Fireman> firemanFromDB = firemanRepository.findById(fireman.getId());
        assertTrue(firemanFromDB.isPresent());
        assertEquals(fireman.getId(), firemanFromDB.get().getId());
        assertEquals(fire1.getId(), firemanFromDB.get().getExtinguishedFires().get(0).getId());
        assertEquals(fire2.getId(), firemanFromDB.get().getExtinguishedFires().get(1).getId());
    }
    
}
