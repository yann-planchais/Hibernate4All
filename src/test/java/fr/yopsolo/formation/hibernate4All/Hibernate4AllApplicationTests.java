package fr.yopsolo.formation.hibernate4All;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.yopsolo.formation.hibernate4All.config.PersistenceConfigTest;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfigTest.class })
class Hibernate4AllApplicationTests {

	@Test
	void contextLoads() {
	}

}
