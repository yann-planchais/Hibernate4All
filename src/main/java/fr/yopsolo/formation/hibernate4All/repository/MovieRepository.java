package fr.yopsolo.formation.hibernate4All.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.yopsolo.formation.hibernate4All.domain.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class MovieRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieRepository.class);
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public void persist(Movie pMovie) {
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(pMovie)); // Vérifie si l'objet est managé déjà par Hibernate
		entityManager.persist(pMovie);
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(pMovie)); 

	}
	
	public List<Movie> getAll() {
		throw new UnsupportedOperationException();
	}
	
}
