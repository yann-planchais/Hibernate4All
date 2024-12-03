package fr.yopsolo.formation.hibernate4All.repository;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.yopsolo.formation.hibernate4All.domain.Actor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class ActorRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActorRepository.class);

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void persist(Actor pActor) {
		entityManager.persist(pActor);
	}

	public Actor findById(Long id) {
		return entityManager.find(Actor.class, id);
	}

	public List<Actor> getAll() {
		return entityManager.createQuery("from Actor", Actor.class).getResultList();
	}

	@Transactional
	public Actor merge(Actor pActor) {
		return entityManager.merge(pActor);

	}

	@Transactional
	public Optional<Actor> update(Actor pActor) {

		if (pActor == null || pActor.getId() == null) {
			return Optional.empty();
		}
		Actor actorBdd = entityManager.find(Actor.class, pActor.getId());
		if (actorBdd != null) {
			actorBdd.setBirthdate(pActor.getBirthdate());
			actorBdd.setName(pActor.getName());
			actorBdd.setFirstname(pActor.getFirstname());
		}
		return Optional.ofNullable(actorBdd);
	}

	@Transactional
	public void remove(Long pId) {
		Actor actor = entityManager.find(Actor.class, pId);
		entityManager.remove(actor);

	}

	@Transactional
	public boolean delete(Long pIdActor) {
		boolean result = false;

		if (pIdActor != null) {
			Actor actorBdd = entityManager.find(Actor.class, pIdActor);
			if (actorBdd != null) {
				entityManager.remove(actorBdd);
				result = true;
			}
		}
		return result;
	}

	/**
	 * Ne récupère pas l'entité en base, ne récupère que la référence de l'objet =>
	 * proxy <br />
	 * Il n'y a pas de requête faite en base : la requête sera effectué lorsqu'on va
	 * faire actor.getXXX() < br /> On peut récupérer réellement l'objet tant qu'on
	 * est dans la session.
	 *
	 * @param pPrimaryKey
	 * @return
	 */
	@Transactional
	public Actor getReference(Long pPrimaryKey) {
		Actor actor = entityManager.getReference(Actor.class, pPrimaryKey);
		// Si on fait ça : LOGGER.trace("Movie name : {}", movie.getName());
		// ça charge toute l'entité avec tous ses attributs
		return actor;
	}

}