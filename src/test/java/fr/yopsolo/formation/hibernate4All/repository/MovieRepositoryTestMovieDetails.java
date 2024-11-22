package fr.yopsolo.formation.hibernate4All.repository;


import org.hibernate.LazyInitializationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.yopsolo.formation.hibernate4All.config.PersistenceConfigTest;
import fr.yopsolo.formation.hibernate4All.domain.Certification;
import fr.yopsolo.formation.hibernate4All.domain.MovieDetails;
import fr.yopsolo.formation.hibernate4All.domain.MovieWithDescription;
import fr.yopsolo.formation.hibernate4All.domain.Review;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfigTest.class })
@SqlConfig(dataSource = "dataSourceH2Test", transactionManager = "transactionManagerDeTest")
@Sql(value = { "/datas/init-data-movie-details.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class MovieRepositoryTestMovieDetails {

	@Autowired
	private MovieWithDescriptionRepository repository;

	/**
	 * 
	 */
	@Test
	public void addMovie_casNominal() {
	
		MovieDetails movieDetails = new MovieDetails();
		movieDetails.setPlot("quel suspens");

		repository.addMovieDetails(movieDetails, -2L);
		assertThat(movieDetails.getId()).as("On aurait du avoir l'id du movie")
		.isEqualTo(-2L);

	}

	@Test
	public void save_MethodeAdditionReview() {
		MovieWithDescription movie = new MovieWithDescription();
		movie.setName("Inception 2");
		movie.setDescription("Film enorme");
		movie.setCertification(Certification.INTERDIT_MOINS_12);

		Review review1 = new Review();
		review1.setAuthor("paul");
		review1.setContent("content 1");

		Review review2 = new Review();
		review2.setAuthor("mary");
		review2.setContent("content 2");

		movie.addReview(review1);
		movie.addReview(review2);

		repository.persist(movie);

	}

	@Test
	public void testGet_lazyExceptionSurReview() {
		MovieWithDescription movie = repository.findById(-1L);
		
		assertThrows(LazyInitializationException.class, () -> movie.getReviews().size());
	}
}
