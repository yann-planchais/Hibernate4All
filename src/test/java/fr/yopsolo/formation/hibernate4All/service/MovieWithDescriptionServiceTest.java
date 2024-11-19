package fr.yopsolo.formation.hibernate4All.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.yopsolo.formation.hibernate4All.config.PersistenceConfigTest;
import fr.yopsolo.formation.hibernate4All.domain.MovieWithDescription;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfigTest.class })
@SqlConfig(dataSource = "dataSourceH2Test", transactionManager = "transactionManagerDeTest")
@Sql(value = { "/datas/init-data-moviewithdescription.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
class MovieWithDescriptionServiceTest {

	@Autowired
	private MovieWithDescriptionService service;

	@Test
	void testUpdateDescription() {
		service.updateDescription(-2L, "autre description");

	}

	/**
	 * Permet de voir que par défaut le flushmode est en auto : <br/>
	 * On crée un nouvel objet mais le persist ne le met pas en base (il devient
	 * juste managé) <br />
	 * Pour eviter les problèmes , il fait un flush avant chaque requête en base
	 * (ici avant le getAll) <BR/>
	 * Et ce pour être sur qu'il a bien la base en adéquation avec le code.
	 *
	 */
	@Test
	void testAddMovieThenGetAll() {

		assertThat(service.getAll()).as("On aurait du trouver 2 films ").hasSize(2);
		assertThat(service.recupererFilm(-2L).getName()).as("On aurait du trouver le film Memento")
				.isEqualTo("Memento");

		List<MovieWithDescription> liste = service.addThenGetAll("Memento 2", "Description");
		assertThat(liste).as("On trouve un film de plus").hasSize(3);

		// On supprime le film crée
		for (MovieWithDescription movie : liste) {
			if (movie.getId() > 0) {
				service.delete(movie.getId());
			}
		}

	}

	/**
	 * On vérifie l'ordonnancement du flush : <br/>
	 * Le delete se fait après l'insert <br/>
	 * Du coup, le nom du film devant être unique, ça pète
	 *
	 */
	@Test
	void testRemoveThenAddMovie() {

		assertThat(service.getAll()).as("On aurait du trouver 2 films").hasSize(2);
		assertThat(service.recupererFilm(-2L).getName()).as("On aurait du trouver le film Inception")
				.isEqualTo("Memento");

		assertThrows(DataIntegrityViolationException.class,
				() -> service.removeThenAddMovie(-2L, "Memento", "Nouvelle description"));

	}

}
