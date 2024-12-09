package fr.yopsolo.formation.hibernate4All.heritage.seconde;

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
import fr.yopsolo.formation.hibernate4All.heritage.seconde.domain.AbstraiteSecondeSolutionProduction;
import fr.yopsolo.formation.hibernate4All.heritage.seconde.domain.HeritageSecondeSolutionFilm;
import fr.yopsolo.formation.hibernate4All.heritage.seconde.domain.HeritageSecondeSolutionSerie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@SqlConfig(dataSource = "dataSource", transactionManager = "transactionManagerDeTest")
@Sql(value = {
		"/datas/postgres/heritage/init-data-heritage-second.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class HeritageSecondeRepositoryTest {

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
		/**
		 * AVANT List<AbstraiteProduction> listeProductions = entityManager.createQuery(
		 * "select p from
		 * fr.yopsolo.formation.hibernate4All.heritage.concrete.domain.AbstraiteProduction
		 * p", AbstraiteProduction.class).getResultList();
		 */
		List<AbstraiteSecondeSolutionProduction> listeProductions = entityManager
				.createQuery("select distinct p  from AbstraiteSecondeSolutionProduction p left join fetch p.reviews",
						AbstraiteSecondeSolutionProduction.class)
				.getResultList();

		assertThat(listeProductions).hasSize(3).as("On attent la liste de toutes les productions");
		assertThat(listeProductions.stream().filter(o -> o instanceof HeritageSecondeSolutionFilm)).hasSize(1)
				.as("On ne devrait avoir que les films.");
		assertThat(listeProductions.stream().filter(o -> o instanceof HeritageSecondeSolutionSerie)).hasSize(2)
				.as("On ne devrait avoir que les series. ");
	}

}
