package fr.yopsolo.formation.hibernate4All.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.yopsolo.formation.hibernate4All.domain.MovieWithDescription;
import fr.yopsolo.formation.hibernate4All.service.MovieWithDescriptionService;

@RestController
@RequestMapping("/movie")
public class MovieWithDescriptionController {

	@Autowired
	MovieWithDescriptionService service;

	@PostMapping("/")
	public MovieWithDescription create(@RequestBody MovieWithDescription movie) {
		service.create(movie);
		return movie;
	}

	@GetMapping("/{id}")
	public ResponseEntity<MovieWithDescription> get(@PathVariable("id") Long idMovie) {
		MovieWithDescription film = service.recupererFilm(idMovie);

		if (film == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(film);
		}
	}

	@PutMapping("/")
	public ResponseEntity<MovieWithDescription> update(@RequestBody MovieWithDescription movie) {

		Optional<MovieWithDescription> result = service.update(movie);
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(result.get());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MovieWithDescription> delete(@PathVariable("id") Long idMovie) {
		boolean suppression = service.delete(idMovie);

		if (suppression) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
