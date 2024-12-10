package fr.yopsolo.formation.hibernate4All.heritage.cleetrangere.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.yopsolo.formation.hibernate4All.heritage.cleetrangere.domain.AbstraiteCleEtrangereProduction;
import fr.yopsolo.formation.hibernate4All.heritage.cleetrangere.domain.HeritageCleEtrangereFilm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class HeritageCleEtrangereRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void persist(AbstraiteCleEtrangereProduction pEntite) {
		entityManager.persist(pEntite);
	}

	/**
	 * Ici on aura le contains qui va retourner FALSE car la session sera fermée
	 *
	 * @param id
	 * @return
	 */
	public AbstraiteCleEtrangereProduction findById(Long id) {
		AbstraiteCleEtrangereProduction entite = entityManager.find(AbstraiteCleEtrangereProduction.class, id);
		return entite;
	}

	public List<AbstraiteCleEtrangereProduction> getAll() {
		return entityManager.createQuery("from Watchable", AbstraiteCleEtrangereProduction.class).getResultList();
	}

	@Transactional
	public AbstraiteCleEtrangereProduction merge(AbstraiteCleEtrangereProduction movie) {
		return entityManager.merge(movie);

	}

	@Transactional
	public void remove(HeritageCleEtrangereFilm pId) {
		HeritageCleEtrangereFilm entite = entityManager.find(HeritageCleEtrangereFilm.class, pId);
		entityManager.remove(entite);

	}

	@Transactional
	public boolean deleteFilm(Long pIdEntite) {
		boolean result = false;

		if (pIdEntite != null) {
			HeritageCleEtrangereFilm movieBdd = entityManager.find(HeritageCleEtrangereFilm.class, pIdEntite);
			if (movieBdd != null) {
				entityManager.remove(movieBdd);
				result = true;
			}
		}
		return result;
	}

}