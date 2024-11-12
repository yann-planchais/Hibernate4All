package fr.yopsolo.formation.hibernate4All.service;

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

}
