package fr.yopsolo.formation.hibernate4All.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.yopsolo.formation.hibernate4All.config.PersistenceConfig;
import fr.yopsolo.formation.hibernate4All.domain.Certification;
import fr.yopsolo.formation.hibernate4All.domain.MovieWithDescription;
import fr.yopsolo.formation.hibernate4All.domain.Review;
import fr.yopsolo.formation.hibernate4All.repository.MovieWithDescriptionRepository;
import jakarta.validation.ConstraintViolationException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@SqlConfig(dataSource = "dataSource", transactionManager = "transactionManagerDeTest")
@Sql(value = {
		"/datas/postgres/init-data-moviewithdescription.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class ReviewValidationTest {

	@Autowired
	private MovieWithDescriptionRepository repository;

	@Test
	void testReviewRatingValidationOK() {
		MovieWithDescription movie = new MovieWithDescription();
		movie.setName("Fight Club");
		movie.setDescription("Film enorme");
		movie.setCertification(Certification.INTERDIT_MOINS_12);

		Review review1 = new Review();
		review1.setAuthor("paul");
		review1.setContent("content 1");
		review1.setRating(8);
		movie.addReview(review1);
		repository.persist(movie);

	}

	@Test
	void testReviewRatingValidationKO() {
		MovieWithDescription movie = new MovieWithDescription();
		movie.setName("Fight Club");
		movie.setDescription("Film enorme");
		movie.setCertification(Certification.INTERDIT_MOINS_12);

		Review review1 = new Review();
		review1.setAuthor("paul");
		review1.setContent("content 1");
		review1.setRating(12);
		movie.addReview(review1);

		Exception e = catchException(() -> repository.persist(movie));

		assertThat(e.getClass().getSimpleName()).as("On aurait du avoir une TransactionSystemException")
				.isEqualTo("TransactionSystemException");

		assertThat(e.getCause().getClass().getSimpleName()).as("On aurait du avoir une RollbackException")
				.isEqualTo("RollbackException");
		assertThat(e.getCause().getCause().getClass().getSimpleName())
				.as("On aurait du avoir une ConstraintViolationException")
				.isEqualTo("ConstraintViolationException");

		ConstraintViolationException ex = (ConstraintViolationException) e.getCause().getCause();
		assertThat(ex.getConstraintViolations()).as("On aurait du une seule erreur de validation.").hasSize(1);
		assertThat(ex.getConstraintViolations().iterator().next().getMessage()).as("On aurait du un message d'erreur.")
				.isEqualTo("Valeur trop haute");
	}
}
