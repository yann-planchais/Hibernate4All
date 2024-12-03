package fr.yopsolo.formation.hibernate4All.heritage.concrete.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.yopsolo.formation.hibernate4All.heritage.concrete.domain.HeritageConcreteFilm;
import fr.yopsolo.formation.hibernate4All.heritage.concrete.domain.AbstraiteProduction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class HeritageConcreteRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(HeritageConcreteRepository.class);

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void persist(AbstraiteProduction pEntite) {
		entityManager.persist(pEntite);
	}

	/**
	 * Ici on aura le contains qui va retourner FALSE car la session sera fermée
	 *
	 * @param id
	 * @return
	 */
	public AbstraiteProduction findById(Long id) {
		AbstraiteProduction entite = entityManager.find(AbstraiteProduction.class, id);
		return entite;
	}

	public List<AbstraiteProduction> getAll() {
		return entityManager.createQuery("from Watchable", AbstraiteProduction.class).getResultList();
	}

	@Transactional
	public AbstraiteProduction merge(AbstraiteProduction movie) {
		return entityManager.merge(movie);

	}

	@Transactional
	public void remove(HeritageConcreteFilm pId) {
		HeritageConcreteFilm entite = entityManager.find(HeritageConcreteFilm.class, pId);
		entityManager.remove(entite);

	}

	@Transactional
	public boolean deleteFilm(Long pIdEntite) {
		boolean result = false;

		if (pIdEntite != null) {
			HeritageConcreteFilm movieBdd = entityManager.find(HeritageConcreteFilm.class, pIdEntite);
			if (movieBdd != null) {
				entityManager.remove(movieBdd);
				result = true;
			}
		}
		return result;
	}

}