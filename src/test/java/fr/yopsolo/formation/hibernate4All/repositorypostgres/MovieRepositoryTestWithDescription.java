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
import fr.yopsolo.formation.hibernate4All.domain.Actor;
import fr.yopsolo.formation.hibernate4All.domain.Certification;
import fr.yopsolo.formation.hibernate4All.domain.MovieWithDescription;
import fr.yopsolo.formation.hibernate4All.repository.ActorRepository;
import fr.yopsolo.formation.hibernate4All.repository.MovieWithDescriptionRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@SqlConfig(dataSource = "dataSource", transactionManager = "transactionManagerDeTest")
@Sql(value = {
		"/datas/postgres/init-data-moviewithdescriptionWithActors.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class MovieRepositoryTestWithDescription {

	@Autowired
	private MovieWithDescriptionRepository repository;

	@Autowired
	private ActorRepository actorRepository;

	@Test
	void save_casNominal() {
		MovieWithDescription movie = new MovieWithDescription();
		movie.setName("Inception 2");
		movie.setDescription("Film enorme");
		movie.setCertification(Certification.INTERDIT_MOINS_12);
		repository.persist(movie);

	}

	@Test
	void save_withActor() {
		MovieWithDescription movie = new MovieWithDescription();
		movie.setNameFluent("The social Network");
		repository.persist(movie);

		Actor zuck = new Actor().setName("Eisenberg").setFirstname("Steve");
		actorRepository.persist(zuck);
		repository.addActor(movie, zuck, "Mark Zuckerberg");
	}

	@Test
	void addActor_existingMovie() {
		MovieWithDescription movie = repository.findById(-1L);

		Actor zuck = new Actor().setName("Eisenberg").setFirstname("Steve");
		actorRepository.persist(zuck);
		repository.addActor(movie, zuck, "Mark Zuckerberg");

	}

	@Test
	void removeActor_existingMovie() {
		MovieWithDescription movie = repository.findById(-1L);

		Actor zuck = new Actor().setId(-1L);
		repository.removeActor(movie, zuck);

	}

}
