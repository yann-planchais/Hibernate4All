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
import fr.yopsolo.formation.hibernate4All.requetage.domain.RequetageJPQLJoinAward;
import fr.yopsolo.formation.hibernate4All.requetage.domain.RequetageJPQLJoinGenre;
import fr.yopsolo.formation.hibernate4All.requetage.domain.RequetageJPQLJoinMovie;
import fr.yopsolo.formation.hibernate4All.requetage.domain.RequetageJPQLJoinReview;
import fr.yopsolo.formation.hibernate4All.requetage.repository.RequetageJPQLJoinRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@SqlConfig(dataSource = "dataSource", transactionManager = "transactionManagerDeTest")
@Sql(value = {
		"/datas/postgres/requetage/init-data-requetage_jpqljoin.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
public class MovieJPQLJoinRepositoryTest {

	@Autowired
	private RequetageJPQLJoinRepository repository;

	@Test
	void save_casNominal() {
		RequetageJPQLJoinMovie movie = new RequetageJPQLJoinMovie();
		movie.setName("Inception 2");
		movie.setDescription("Film enorme");
		movie.setCertification(RequetageCertification.INTERDIT_MOINS_12);
		movie.addAward(new RequetageJPQLJoinAward().setAnnee(2011).setName("Best Movie"));
		movie.addGenre(new RequetageJPQLJoinGenre().setName("Horreur"));
		movie.addReview(new RequetageJPQLJoinReview().setRating(2).setAuthor("Dede").setContent("film ok"));

		repository.persist(movie);
	}

	@Test
	void getMoviesWithReviews_casNominal() {
		List<RequetageJPQLJoinMovie> movies = repository.getMoviesWithReviewJPQL();
		assertThat(movies).as("On retrouve le film").hasSize(2);
		RequetageJPQLJoinMovie inception = movies.stream().filter(m -> m.getId().equals(-1L)).findFirst().get();
		assertThat(inception.getRequetageJPQLJoinReviews()).as("On retrouve les revues").hasSize(2);
	}
}
