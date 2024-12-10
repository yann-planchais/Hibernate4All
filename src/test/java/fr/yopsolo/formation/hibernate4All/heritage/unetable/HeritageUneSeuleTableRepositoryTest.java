package fr.yopsolo.formation.hibernate4All.heritage.unetable;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.yopsolo.formation.hibernate4All.config.PersistenceConfig;
import fr.yopsolo.formation.hibernate4All.heritage.unetable.domain.AbstraiteUneSeuleTableProduction;
import fr.yopsolo.formation.hibernate4All.heritage.unetable.domain.HeritageUneSeuleTableFilm;
import fr.yopsolo.formation.hibernate4All.heritage.unetable.domain.HeritageUneSeuleTableSerie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@SqlConfig(dataSource = "dataSource", transactionManager = "transactionManagerDeTest")
@Sql(value = {
		"/datas/postgres/heritage/init-data-heritage-unetable.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class HeritageUneSeuleTableRepositoryTest {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Changement avec solution 1 : <br />
	 * <ul>
	 * <li>plus le package entier car c'est une entité
	 * <li>on peut récupérér les associations (ici reviews)
	 * <li>un seul select avec union au lieu de 2 selects distincts
	 * </ul>
	 */
	@Test
	void testInherence_recuperation() {

		List<AbstraiteUneSeuleTableProduction> listeProductions = entityManager
				.createQuery("select distinct p  from AbstraiteUneSeuleTableProduction p left join fetch p.reviews",
						AbstraiteUneSeuleTableProduction.class)
				.getResultList();

		assertThat(listeProductions).hasSize(3).as("On attent la liste de toutes les productions");
		assertThat(listeProductions.stream().filter(o -> o instanceof HeritageUneSeuleTableFilm)).hasSize(1)
				.as("On ne devrait avoir que les films.");
		assertThat(listeProductions.stream().filter(o -> o instanceof HeritageUneSeuleTableSerie)).hasSize(2)
				.as("On ne devrait avoir que les series. ");
	}

}
