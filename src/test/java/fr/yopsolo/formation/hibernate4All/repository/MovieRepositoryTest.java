package fr.yopsolo.formation.hibernate4All.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.yopsolo.formation.hibernate4All.config.PersistenceConfig;
import fr.yopsolo.formation.hibernate4All.domain.Movie;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
public class MovieRepositoryTest {
	
	@Autowired
	private MovieRepository repository;
	
	@Test
	public void save_casNominal() {
		Movie movie = new Movie();
		movie.setName("Inception");			
		repository.persist(movie);

	}

}
