package fr.yopsolo.formation.hibernate4All.repositorypostgres;

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
import fr.yopsolo.formation.hibernate4All.repository.MovieWithDescriptionRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@SqlConfig(dataSource = "dataSource", transactionManager = "transactionManagerDeTest")
@Sql(value = {
		"/datas/postgres/init-data-moviewithdescription.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
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

}
