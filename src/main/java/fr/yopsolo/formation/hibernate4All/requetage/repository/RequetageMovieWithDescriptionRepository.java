package fr.yopsolo.formation.hibernate4All.requetage.repository;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.yopsolo.formation.hibernate4All.requetage.domain.RequetageMovieWithDescription;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class RequetageMovieWithDescriptionRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequetageMovieWithDescriptionRepository.class);

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void persist(RequetageMovieWithDescription pMovie) {
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(pMovie));
		/*
		 * Vérifie si l'objet est managé déjà par Hibernate Si on est dans une
		 * transaction, on a true Sinon on false
		 */
		entityManager.persist(pMovie);
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(pMovie));

	}

	/**
	 * Ici on aura le contains qui va retourner TRUE
	 *
	 * @param id
	 * @return
	 */
	@Transactional
	public RequetageMovieWithDescription findByIdAvecTransaction(Long id) {
		RequetageMovieWithDescription movie = entityManager.find(RequetageMovieWithDescription.class, id);
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(movie));
		return movie;
	}

	public List<RequetageMovieWithDescription> getAll() {
		return entityManager.createQuery("from MovieWithDescription", RequetageMovieWithDescription.class)
				.getResultList();
	}

	@Transactional
	public RequetageMovieWithDescription merge(RequetageMovieWithDescription movie) {
		return entityManager.merge(movie);

	}

	@Transactional
	public Optional<RequetageMovieWithDescription> update(RequetageMovieWithDescription movie) {

		if (movie == null || movie.getId() == null) {
			return Optional.empty();
		}
		RequetageMovieWithDescription movieBdd = entityManager.find(RequetageMovieWithDescription.class, movie.getId());
		if (movieBdd != null) {
			movieBdd.setDescription(movie.getDescription());
			movieBdd.setName(movie.getName());
		}
		return Optional.ofNullable(movieBdd);
	}

	@Transactional
	public void remove(Long pId) {
		RequetageMovieWithDescription movie = entityManager.find(RequetageMovieWithDescription.class, pId);
		entityManager.remove(movie);

	}

	/**
	 * JPQL name fait référence au nom de l'attribut et non à la colonne en BDD
	 *
	 * @param pNom
	 * @return
	 */
	public List<RequetageMovieWithDescription> findByNameJPQL(String pNom) {
		return entityManager
				.createQuery("select m from RequetageMovieWithDescription m where m.name= :param",
						RequetageMovieWithDescription.class)
				.setParameter("param", pNom)
				.getResultList();
	}
}