package fr.yopsolo.formation.hibernate4All.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.yopsolo.formation.hibernate4All.config.PersistenceConfig;
import fr.yopsolo.formation.hibernate4All.domain.Movie;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@SqlConfig(dataSource = "dataSourceH2Test", transactionManager = "transactionManagerDeTest")
@Sql(value = { "/datas/init-data-movie.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository repository;

	@Test
	public void save_casNominal() {
		Movie movie = new Movie();
		movie.setName("Inception");
		repository.persist(movie);

	}

	@Test
	public void merge_casSimuleObjetDetache() {
		// pas d'update
		Movie movieIdentique = new Movie();
		movieIdentique.setName("Inception");
		movieIdentique.setId(-1L);
		Movie retour = repository.merge(movieIdentique);
		assertThat(retour.getName()).as("On aurait du trouver le film Inception").isEqualTo("Inception");

		// update
		Movie movieDifferent = new Movie();
		movieDifferent.setName("Inception 2");
		movieDifferent.setId(-1L);
		retour = repository.merge(movieDifferent);
		assertThat(retour.getName()).as("On aurait du trouver le film Inception 2").isEqualTo("Inception 2");

	}

	@Test
	public void findById_casNominal() {
		Movie movie = repository.findById(-1L);
		assertThat(movie.getName()).as("On aurait du trouver le film Inception").isEqualTo("Inception");

	}

	@Test
	public void findById_casTransactionnal() {
		Movie movie = repository.findByIdAvecTransaction(-2L);
		assertThat(movie.getName()).as("On aurait du trouver le film Inception").isEqualTo("Memento");

	}

	@Test
	public void getAll_casNominal() {

		List<Movie> movies = repository.getAll();
		assertThat(movies).as("On devrait avoir 2 films").hasSize(2);
		;
	}

	@Test
	public void removed_casNominal() {
		repository.remove(-1L);
		Movie movie = repository.findById(-1L);
		assertThat(movie).as("On n'aurait pas du rertouver le film Inception car supprimé").isNull();
	}

	@Test
	void testGetReferenceNominal() {
		Movie movie = repository.getReference(-1L);
		assertThat(movie.getId()).as("la référence a bien été chargée.").isEqualTo(-1L);

	}

	@Test
	void testGetReferenceLazyInitialization() {
		Movie movie = repository.getReferenceSansRequeteEnBDD(-1L);

		assertThrows(LazyInitializationException.class, () -> movie.getName());

		Exception e = catchException(() -> movie.getName());
		assertThat(e.getClass().getSimpleName()).as("On aurait du avoir une lazy Exception")
				.isEqualTo("LazyInitializationException");

	}
}
