package fr.yopsolo.formation.hibernate4All.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("/create")
	public MovieWithDescription movieCreate(@RequestBody MovieWithDescription movie) {

		service.create(movie);

		return movie;
	}
}
