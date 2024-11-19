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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfigTest.class })
@SqlConfig(dataSource = "dataSourceH2Test", transactionManager = "transactionManagerDeTest")
@Sql(value = { "/datas/init-data-moviewithdescription.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class MovieRepositoryTestWithDescription {

	@Autowired
	private MovieWithDescriptionRepository repository;

	@Test
	public void save_casNominal() {
		MovieWithDescription movie = new MovieWithDescription();
		movie.setName("Inception 2");
		movie.setDescription("Film enorme");
		movie.setCertification(Certification.INTERDIT_MOINS_12);
		repository.persist(movie);

	}

	@Test
	public void save_casNominalFLuent() {
		MovieWithDescription movie = new MovieWithDescription().setNameFluent("Inception 3")
				.setDescriptionFluent("Film enorme")
				.setCertificationFluent(Certification.INTERDIT_MOINS_12);
		repository.persist(movie);

	}

	@Test
	public void findById_casNominal() {
		MovieWithDescription movie = repository.findById(-1L);
		assertThat(movie.getName()).as("On aurait du trouver le film Inception").isEqualTo("Inception");
		assertThat(movie.getCertification()).as("On aurait du trouver la bonne certif")
				.isEqualTo(Certification.TOUS_PUBLIC);

	}

}
