package fr.yopsolo.formation.hibernate4All.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.yopsolo.formation.hibernate4All.domain.MovieWithDescription;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class MovieWithDescriptionRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieWithDescriptionRepository.class);

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void persist(MovieWithDescription pMovie) {
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(pMovie));
		/*
		 * Vérifie si l'objet est managé déjà par Hibernate Si on est dans une
		 * transaction, on a true Sinon on false
		 */
		entityManager.persist(pMovie);
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(pMovie));

	}

	@Transactional
	public void persistAvecFlushForce(MovieWithDescription pMovie) {
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(pMovie));
		/*
		 * Vérifie si l'objet est managé déjà par Hibernate Si on est dans une
		 * transaction, on a true Sinon on false
		 */
		entityManager.persist(pMovie);
		entityManager.flush();
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(pMovie));

	}

	/**
	 * Ici on aura le contains qui va retourner FALSE car la session sera fermée
	 *
	 * @param id
	 * @return
	 */
	public MovieWithDescription findById(Long id) {
		MovieWithDescription movie = entityManager.find(MovieWithDescription.class, id);
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(movie));
		return movie;
	}

	/**
	 * Ici on aura le contains qui va retourner TRUE
	 *
	 * @param id
	 * @return
	 */
	@Transactional
	public MovieWithDescription findByIdAvecTransaction(Long id) {
		MovieWithDescription movie = entityManager.find(MovieWithDescription.class, id);
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(movie));
		return movie;
	}

	public List<MovieWithDescription> getAll() {
		return entityManager.createQuery("from Movie", MovieWithDescription.class).getResultList();
	}

	@Transactional
	public MovieWithDescription merge(MovieWithDescription movie) {
		return entityManager.merge(movie);

	}

	@Transactional
	public void remove(Long pId) {

		MovieWithDescription movie = entityManager.find(MovieWithDescription.class, pId);
		entityManager.remove(movie);

	}

	/**
	 * Ne récupère pas l'entité en base, ne récupère que la référence de l'objet =>
	 * proxy <br />
	 * Il n'y a pas de requête faite en base : la requête sera effectué lorsqu'on va
	 * faire movie.getName() < br /> On peut récupérer réellement l'objet tant qu'on
	 * est dans la session.
	 *
	 * @param pPrimaryKey
	 * @return
	 */
	@Transactional
	public MovieWithDescription getReference(Long pPrimaryKey) {
		MovieWithDescription movie = entityManager.getReference(MovieWithDescription.class, pPrimaryKey);
		LOGGER.trace("Movie name : {}", movie.getName());
		return movie;
	}

	/**
	 * Plus de requête donc @Transactional non utile
	 *
	 * @param pPrimaryKey
	 * @return
	 */
	public MovieWithDescription getReferenceSansRequeteEnBDD(Long pPrimaryKey) {
		MovieWithDescription movie = entityManager.getReference(MovieWithDescription.class, pPrimaryKey);
		return movie;
	}
}