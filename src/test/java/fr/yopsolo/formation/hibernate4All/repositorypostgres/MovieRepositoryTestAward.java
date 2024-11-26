package fr.yopsolo.formation.hibernate4All.repositorypostgres;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
import fr.yopsolo.formation.hibernate4All.domain.Award;
import fr.yopsolo.formation.hibernate4All.domain.Certification;
import fr.yopsolo.formation.hibernate4All.domain.MovieWithDescription;
import fr.yopsolo.formation.hibernate4All.repository.MovieWithDescriptionRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@SqlConfig(dataSource = "dataSource", transactionManager = "transactionManagerDeTest")
@Sql(value = {
		"/datas/postgres/init-data-moviewithdescription.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class MovieRepositoryTestAward {

	@Autowired
	private MovieWithDescriptionRepository repository;

	@Test
	public void save_MethodeAvecSetter() {
		MovieWithDescription movie = new MovieWithDescription();
		movie.setName("Inception 1");
		movie.setDescription("Film enorme");
		movie.setCertification(Certification.INTERDIT_MOINS_12);

		Award award1 = new Award();
		award1.setName("Oscar");
		award1.setYear(2020);

		Award award2 = new Award();
		award2.setName("Cesar");
		award2.setYear(2020);

		movie.addAward(award1);
		movie.addAward(award2);

		repository.persist(movie);

	}

	@Test
	public void testAddAward_UnsupportedOperationException() {
		MovieWithDescription movie = repository.findById(-1L);

		Award award1 = new Award();
		award1.setName("Oscar");
		award1.setYear(2020);

		assertThrows(UnsupportedOperationException.class, () -> movie.getAwards().add(award1));
	}

	@Test
	public void testGet_lazyExceptionSurReview() {
		MovieWithDescription movie = repository.findById(-1L);

		assertThrows(LazyInitializationException.class, () -> movie.getAwards().size());
	}
}
