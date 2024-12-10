package fr.yopsolo.formation.hibernate4All.heritage.unetable.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.yopsolo.formation.hibernate4All.heritage.unetable.domain.AbstraiteUneSeuleTableProduction;
import fr.yopsolo.formation.hibernate4All.heritage.unetable.domain.HeritageUneSeuleTableFilm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class HeritageUneSeuleTableRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void persist(AbstraiteUneSeuleTableProduction pEntite) {
		entityManager.persist(pEntite);
	}

	/**
	 * Ici on aura le contains qui va retourner FALSE car la session sera fermée
	 *
	 * @param id
	 * @return
	 */
	public AbstraiteUneSeuleTableProduction findById(Long id) {
		AbstraiteUneSeuleTableProduction entite = entityManager.find(AbstraiteUneSeuleTableProduction.class, id);
		return entite;
	}

	public List<AbstraiteUneSeuleTableProduction> getAll() {
		return entityManager.createQuery("from Watchable", AbstraiteUneSeuleTableProduction.class).getResultList();
	}

	@Transactional
	public AbstraiteUneSeuleTableProduction merge(AbstraiteUneSeuleTableProduction movie) {
		return entityManager.merge(movie);

	}

	@Transactional
	public void remove(HeritageUneSeuleTableFilm pId) {
		HeritageUneSeuleTableFilm entite = entityManager.find(HeritageUneSeuleTableFilm.class, pId);
		entityManager.remove(entite);

	}

	@Transactional
	public boolean deleteFilm(Long pIdEntite) {
		boolean result = false;

		if (pIdEntite != null) {
			HeritageUneSeuleTableFilm movieBdd = entityManager.find(HeritageUneSeuleTableFilm.class, pIdEntite);
			if (movieBdd != null) {
				entityManager.remove(movieBdd);
				result = true;
			}
		}
		return result;
	}

}