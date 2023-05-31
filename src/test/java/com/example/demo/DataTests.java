import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import com.example.demo.model.Fire;
import com.example.demo.model.Fireman;
import com.example.demo.repository.FireRepository;
import com.example.demo.repository.FiremanRepository;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

@DataJpaTest
public class DataTests {

    @Autowired
    FireRepository fireRepository;

    @Autowired
    FiremanRepository firemanRepository;

    @BeforeEach
    public void setup() {
        fireRepository.deleteAll();
        firemanRepository.deleteAll();
    }

    @Test
    public void testGetVeteranWithMultipleFiremen() {
        //  creer des nouveau pompier
        Fireman fireman1 = createFiremanWithFires("John Doe", 5);
        Fireman fireman2 = createFiremanWithFires("Jane Smith", 3);
        Fireman fireman3 = createFiremanWithFires("Mike Johnson", 8);

        // Save en base de donné 
        firemanRepository.saveAll(Arrays.asList(fireman1, fireman2, fireman3));

        // retrouvé le vétéran 
        Optional<Fireman> veteran = firemanRepository.getVeteran();

        // check resulta 
        Assertions.assertTrue(veteran.isPresent());
        Assertions.assertEquals("Mike Johnson", veteran.get().getName());
    }

    @Test
    public void testGetVeteranWithSingleFireman() {
        // Create a fireman with fires
        Fireman fireman = createFiremanWithFires("John Doe", 10);

        // Save fireman in bdd
        firemanRepository.save(fireman);

        // get veteran 
        Optional<Fireman> veteran = firemanRepository.getVeteran();

        // check resultat
        Assertions.assertTrue(veteran.isPresent());
        Assertions.assertEquals("John Doe", veteran.get().getName());
    }

    @Test
    public void testGetVeteranWithNoFireman() {
        // Retrouver le veteran 
        Optional<Fireman> veteran = firemanRepository.getVeteran();

        // Verify the result
        Assertions.assertTrue(veteran.isEmpty());
    }

    private Fireman createFiremanWithFires(String name, int numFires) {
        Fireman fireman = new Fireman();
        fireman.setName(name);

        for (int i = 0; i < numFires; i++) {
            Fire fire = new Fire(8, Instant.now());
            fireman.getExtinguishedFires().add(fire);
            fireRepository.save(fire);
        }

        return fireman;
    }
}
