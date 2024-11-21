package fr.yopsolo.formation.hibernate4All.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.hibernate.LazyInitializationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.yopsolo.formation.hibernate4All.config.PersistenceConfigTest;
import fr.yopsolo.formation.hibernate4All.domain.Certification;
import fr.yopsolo.formation.hibernate4All.domain.Genre;
import fr.yopsolo.formation.hibernate4All.domain.MovieWithDescription;
import fr.yopsolo.formation.hibernate4All.domain.Review;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfigTest.class })
@SqlConfig(dataSource = "dataSourceH2Test", transactionManager = "transactionManagerDeTest")
@Sql(value = { "/datas/init-data-genre.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class MovieRepositoryTestGenre {

	@Autowired
	private MovieWithDescriptionRepository repository;


	@Test
	public void save_nominal() {
		MovieWithDescription movie = new MovieWithDescription();
		movie.setName("The social network");
		movie.setDescription("Film enorme");				
		Genre genreBio = new Genre();
		genreBio.setName("bio");	
		Genre genreDrama = new Genre();
		genreDrama.setName("drama");				
		movie.addGenre(genreDrama).addGenre(genreBio);	

		repository.persist(movie);
		
		assertThat(genreBio.getId()).as("L'entité Genre aurait du être persistée").isNotNull();
	}

	/**
	 * Le genre Action est déjà en base et on essaie de faire persisté un objet détaché
	 */
	@Test
	public void save_withExistingGenre() {
		MovieWithDescription movie = new MovieWithDescription();
		movie.setName("The social network");
		movie.setDescription("Film enorme");				
		Genre genreBio = new Genre();
		genreBio.setName("bio");	
		Genre genreDrama = new Genre();
		genreDrama.setName("drama");	
		
		Genre genreAction = new Genre();
		genreAction.setName("Action");
		genreAction.setId(-1L);
		movie.addGenre(genreDrama).addGenre(genreBio).addGenre(genreAction);	

		assertThrows(InvalidDataAccessApiUsageException.class, () -> repository.persist(movie));

	}
	
	@Test
	public void merge_withExistingGenre() {
		MovieWithDescription movie = new MovieWithDescription();
		movie.setName("The social network");
		movie.setDescription("Film enorme");				
		Genre genreBio = new Genre();
		genreBio.setName("bio");	
		Genre genreDrama = new Genre();
		genreDrama.setName("drama");	
		
		Genre genreAction = new Genre();
		genreAction.setName("Action");
		genreAction.setId(-1L);
		movie.addGenre(genreDrama).addGenre(genreBio).addGenre(genreAction);	

		repository.merge(movie);


	}
}
