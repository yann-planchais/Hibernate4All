package fr.yopsolo.formation.hibernate4All.requetage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.yopsolo.formation.hibernate4All.config.PersistenceConfig;
import fr.yopsolo.formation.hibernate4All.requetage.domain.RequetageCertification;
import fr.yopsolo.formation.hibernate4All.requetage.domain.RequetageMovieWithDescription;
import fr.yopsolo.formation.hibernate4All.requetage.repository.RequetageMovieWithDescriptionRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@SqlConfig(dataSource = "dataSource", transactionManager = "transactionManagerDeTest")
@Sql(value = { "/datas/postgres/requetage/init-data-requetage.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class MovieRepositoryTestWithDescription {

	@Autowired
	private RequetageMovieWithDescriptionRepository repository;

	@Test
	void save_casNominal() {
		RequetageMovieWithDescription movie = new RequetageMovieWithDescription();
		movie.setName("Inception 2");
		movie.setDescription("Film enorme");
		movie.setCertification(RequetageCertification.INTERDIT_MOINS_12);
		repository.persist(movie);
	}

	@Test
	void findbyJPQL() {
		List<RequetageMovieWithDescription> result = repository.findByNameJPQL("Inception");
		assertThat(result).as("On devrait retrouver 1 film").hasSize(1);

	}

	@Test
	void findbyCriteria_Certification_casNominal() {
		List<RequetageMovieWithDescription> result = repository.findByCertificationByCriteria("<=",
				RequetageCertification.INTERDIT_MOINS_12);
		assertThat(result).as("On devrait retrouver 1 film").hasSize(1);

	}

}
