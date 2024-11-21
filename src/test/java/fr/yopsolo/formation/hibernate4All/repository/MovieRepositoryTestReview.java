package fr.yopsolo.formation.hibernate4All.repository;

import static org.assertj.core.api.Assertions.assertThat;

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
import fr.yopsolo.formation.hibernate4All.domain.MovieWithDescription;
import fr.yopsolo.formation.hibernate4All.domain.Review;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfigTest.class })
@SqlConfig(dataSource = "dataSourceH2Test", transactionManager = "transactionManagerDeTest")
@Sql(value = { "/datas/init-data-review.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class MovieRepositoryTestReview {

	@Autowired
	private MovieWithDescriptionRepository repository;

	/**
	 * Du coup le champ movie_id dans la table review est NULL !!!
	 */
	@Test
	public void save_MethodeAvecSetter() {
		MovieWithDescription movie = new MovieWithDescription();
		movie.setName("Inception 1");
		movie.setDescription("Film enorme");
		movie.setCertification(Certification.INTERDIT_MOINS_12);
		
		Review review1 = new Review();
		review1.setAuthor("paul");	
		review1.setContent("content 1");

		Review review2 = new Review();
		review2.setAuthor("mary");	
		review2.setContent("content 2");
		
		movie.getReviews().add(review1);
		movie.getReviews().add(review2);
		
		repository.persist(movie);

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

}
