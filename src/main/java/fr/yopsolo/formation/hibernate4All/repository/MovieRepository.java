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
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(pMovie)); 
		/* Vérifie si l'objet est managé déjà par Hibernate
		 * Si on est dans une transaction, on a true 
		 * Sinon on  false
		*/
		entityManager.persist(pMovie);
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(pMovie)); 

	}
	
	@Transactional
	public void persistAvecFlushForce(Movie pMovie) {
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(pMovie)); 
		/* Vérifie si l'objet est managé déjà par Hibernate
		 * Si on est dans une transaction, on a true 
		 * Sinon on  false
		*/
		entityManager.persist(pMovie);
		entityManager.flush();
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(pMovie)); 

	}
	
	/**
	 * Ici on aura le contains qui va retourner FALSE car la session sera fermée
	 * @param id
	 * @return
	 */
	public Movie findById(Long id) {
		Movie movie = entityManager.find(Movie.class, id);
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(movie)); 
		return movie;
	}
	
	/**
	 * Ici on aura le contains qui va retourner TRUE
	 * @param id
	 * @return
	 */
	@Transactional
	public Movie findByIdAvecTransaction(Long id) {
		Movie movie = entityManager.find(Movie.class, id);
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(movie)); 
		return movie;
	}
	
	public List<Movie> getAll() {
		return entityManager.createQuery("from Movie", Movie.class).getResultList();
	}


	@Transactional
	public Movie merge(Movie movie) {
		return entityManager.merge(movie);
		
	}
	
	
	@Transactional
	public void remove(Long pId) {
		
		Movie movie = entityManager.find(Movie.class, pId);
		entityManager.remove(movie);
		
	}
	
	/**
	 * Ne récupère pas l'entité en base, ne récupère que la référence de l'objet => proxy <br />
	 * Il n'y a pas de requête faite en base : la requête sera effectué lorsqu'on va faire movie.getName() < br />
	 * On peut récupérer réellement l'objet tant qu'on est dans la session.
	 * @param pPrimaryKey
	 * @return
	 */
	@Transactional
	public Movie getReference(Long pPrimaryKey) {
		Movie movie = 
		 entityManager.getReference(Movie.class, pPrimaryKey);
		LOGGER.trace("Movie name : {}", movie.getName());
		return movie;
	}

	/**
	 * Plus de requête donc @Transactional non utile
	 * @param pPrimaryKey
	 * @return
	 */
	public Movie getReferenceSansRequeteEnBDD(Long pPrimaryKey) {
		Movie movie = 
		 entityManager.getReference(Movie.class, pPrimaryKey);
		return movie;
	}
}