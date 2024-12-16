package fr.yopsolo.formation.hibernate4All.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.yopsolo.formation.hibernate4All.config.PersistenceConfigTest;
import fr.yopsolo.formation.hibernate4All.repository.MovieWithDescriptionRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfigTest.class })
@SqlConfig(dataSource = "dataSourceH2Test", transactionManager = "transactionManagerDeTest")
@Sql(value = { "/datas/init-data-moviewithdescription.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class EqualsAndHashcodeTest {

	@Autowired
	private MovieWithDescriptionRepository repository;

	/**
	 * Ici si on avait gardé l'ancienne implémentation de equals, le 2eme test
	 * aurait échoué :
	 *
	 * <pre>
	 * public boolean equals(Object obj) {
	 * 	if (this == obj) {
	 * 		return true;
	 * 	}
	 * 	if (obj == null) {
	 * 		return false;
	 * 	}
	 * 	if (getClass() != obj.getClass()) {
	 * 		return false;
	 * 	}
	 * 	MovieWithDescription other = (MovieWithDescription) obj;
	 * 	return certification == other.certification && Objects.equals(description, other.description)
	 * 			&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	 * }
	 * </pre>
	 */
	@Test
	void testEquals() {
		MovieWithDescription movie = new MovieWithDescription();
		movie.setName("Dune Equals");
		repository.persist(movie);

		MovieWithDescription movieBDD = repository.findById(movie.getId());

		assertThat(movie.equals(movieBDD)).as("Il devrait y avoir égalité entre les 2 entités").isTrue();

		MovieWithDescription movieRef = repository.getReference(movie.getId());
		assertThat(movie.equals(movieRef)).as("Il devrait y avoir égalité entre l'entité et sa référence").isTrue();

	}

	@Test
	void testHashcode() {
		MovieWithDescription movie = new MovieWithDescription();
		movie.setName("Dune");

		Set<MovieWithDescription> monSet = new HashSet<>();
		monSet.add(movie);
		assertThat(monSet.contains(movie)).as("Il devrait y avoir le film").isTrue();

		repository.persist(movie);
		assertThat(monSet.contains(movie)).as("Il devrait y avoir le film vu que le hashet n'a pas changé").isTrue();

	}

}
