package fr.yopsolo.formation.hibernate4All.requetage.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.yopsolo.formation.hibernate4All.requetage.domain.RequetageJPQLJoinMovie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class RequetageJPQLJoinRepository {

	@PersistenceContext
	EntityManager entityManager;

	private static final Logger LOGGER = LoggerFactory.getLogger(RequetageJPQLJoinRepository.class);

	@Transactional
	public void persist(RequetageJPQLJoinMovie pMovie) {
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(pMovie));
		/*
		 * Vérifie si l'objet est managé déjà par Hibernate Si on est dans une
		 * transaction, on a true Sinon on false
		 */
		entityManager.persist(pMovie);
		LOGGER.trace("entityManager.contains() : {}", entityManager.contains(pMovie));

	}

	/**
	 * select m.* from movies m left join review r on m.id = r.movie_id where m.name
	 * = ''; => select m from RequetageJPQLJoinMovie m left join
	 * m.requetageJPQLJoinReviews <br/>
	 * On ne récupère pas les reviews. Il faut faire un fetch <br />
	 * select m.* from movies m left join review r on m.id = r.movie_id where m.name
	 * = ''; <br />
	 * => select m from RequetageJPQLJoinMovie m left join fetch
	 * m.requetageJPQLJoinReviews
	 *
	 * @return
	 */
	public List<RequetageJPQLJoinMovie> getMoviesWithReviewJPQL() {
		return entityManager
				.createQuery("select m from RequetageJPQLJoinMovie m left join fetch m.requetageJPQLJoinReviews",
						RequetageJPQLJoinMovie.class)
				.getResultList();
	}

}
