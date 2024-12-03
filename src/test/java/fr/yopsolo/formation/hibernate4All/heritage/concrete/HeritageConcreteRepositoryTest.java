package fr.yopsolo.formation.hibernate4All.heritage.concrete;

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
import fr.yopsolo.formation.hibernate4All.heritage.concrete.domain.AbstraiteProduction;
import fr.yopsolo.formation.hibernate4All.heritage.concrete.domain.HeritageConcreteFilm;
import fr.yopsolo.formation.hibernate4All.heritage.concrete.domain.HeritageConcreteSerie;
import fr.yopsolo.formation.hibernate4All.heritage.concrete.repository.HeritageConcreteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@SqlConfig(dataSource = "dataSource", transactionManager = "transactionManagerDeTest")
@Sql(value = {
		"/datas/postgres/heritage/init-data-heritage-concrete.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class HeritageConcreteRepositoryTest {

	@Autowired
	private HeritageConcreteRepository repository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	void testInherence_recuperation() {
		List<AbstraiteProduction> listeProductions = entityManager.createQuery(
				"select p  from fr.yopsolo.formation.hibernate4All.heritage.concrete.domain.AbstraiteProduction p",
				AbstraiteProduction.class).getResultList();

		assertThat(listeProductions).hasSize(3).as("On attent la liste de toutes les productions");
		assertThat(listeProductions.stream().filter(o -> o instanceof HeritageConcreteFilm)).hasSize(1)
				.as("On ne devrait avoir que les films.");
		assertThat(listeProductions.stream().filter(o -> o instanceof HeritageConcreteSerie)).hasSize(2)
				.as("On ne devrait avoir que les series. ");
	}

}
