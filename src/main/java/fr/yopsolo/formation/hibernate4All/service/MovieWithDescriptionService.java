package fr.yopsolo.formation.hibernate4All.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.yopsolo.formation.hibernate4All.domain.MovieWithDescription;
import fr.yopsolo.formation.hibernate4All.repository.MovieWithDescriptionRepository;

@Service
public class MovieWithDescriptionService {

	@Autowired
	private MovieWithDescriptionRepository movieWithDescriptionRepository;

	@Transactional
	public void updateDescription(Long pId, String pDescription) {
		MovieWithDescription movie = movieWithDescriptionRepository.findById(pId);

		movie.setDescription(pDescription);
		// A la fin de la transaction, il va push les modifications
	}

	@Transactional
	public void create(MovieWithDescription pMovie) {
		movieWithDescriptionRepository.persist(pMovie);
	}

	public MovieWithDescription recupererFilm(Long pIdentifiant) {
		return movieWithDescriptionRepository.findById(pIdentifiant);
	}

	@Transactional
	public Optional<MovieWithDescription> update(MovieWithDescription pMovie) {
		return movieWithDescriptionRepository.update(pMovie);
	}

	@Transactional
	public boolean delete(Long pIdMovie) {
		return movieWithDescriptionRepository.delete(pIdMovie);
	}

	@Transactional
	public List<MovieWithDescription> getAll() {
		return movieWithDescriptionRepository.getAll();
	}

	/**
	 * Permet de voir que par défaut le flushmode est en auto : <br/>
	 * On crée un nouvel objet mais le persist ne le met pas en base (il devient
	 * juste managé) <br />
	 * Pour eviter les problèmes , il fait un flush avant chaque requête en base
	 * (ici avant le getAll) <BR/>
	 * Et ce pour être sur qu'il a bien la base en adéquation avec le code.
	 *
	 * Lié avec le TU testAddMovieThenGetAll
	 */
	@Transactional
	public List<MovieWithDescription> addThenGetAll(String pName, String pDescription) {

		MovieWithDescription movie = new MovieWithDescription();
		movie.setName(pName);
		movie.setDescription(pDescription);
		movieWithDescriptionRepository.persist(movie);
		return movieWithDescriptionRepository.getAll();

	}

	/**
	 * Methode permettant de vérifier l'ordre réel des opérations lors du flush
	 * <br />
	 * https://docs.jboss.org/hibernate/orm/6.6/userguide/html_single/Hibernate_User_Guide.html#flushing-order<br
	 * />
	 * Lié avec le TU testRemoveThenAddMovie()
	 */
	@Transactional
	public MovieWithDescription removeThenAddMovie(Long pIdentToRemove, String pName, String pDescription) {
		movieWithDescriptionRepository.delete(pIdentToRemove);
		MovieWithDescription movie = new MovieWithDescription();
		movie.setName(pName);
		movie.setDescription(pDescription);
		movieWithDescriptionRepository.persist(movie);
		return movie;
	}

}
