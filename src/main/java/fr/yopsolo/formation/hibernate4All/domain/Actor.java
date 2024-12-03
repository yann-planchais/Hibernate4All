package fr.yopsolo.formation.hibernate4All.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;

	private String firstname;

	private LocalDate birthdate;

	@OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MovieActor> moviesActors = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public Actor setId(Long pId) {
		id = pId;
		return this;
	}

	public String getName() {
		return name;
	}

	public Actor setName(String pName) {
		name = pName;
		return this;
	}

	public String getFirstname() {
		return firstname;
	}

	public Actor setFirstname(String pFirstname) {
		firstname = pFirstname;
		return this;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public Actor setBirthdate(LocalDate pBirthdate) {
		birthdate = pBirthdate;
		return this;
	}

	public List<MovieActor> getMoviesActors() {
		return moviesActors;
	}

	public void addMovieActor(MovieActor pMovieActor) {
		if (pMovieActor != null) {
			moviesActors.add(pMovieActor);
		}
	}

	public void removeMovieActor(MovieActor pMovieActor) {
		if (pMovieActor != null && moviesActors.contains(pMovieActor)) {
			moviesActors.remove(pMovieActor);
		}
	}

	@Override
	public int hashCode() {
		return 82;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Actor)) {
			return false;

		}
		Actor other = (Actor) obj;
		if (id == null && other.getId() == null) {
			return Objects.equals(birthdate, other.birthdate) && Objects.equals(firstname, other.firstname)
					&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
		}

		return id != null && id.equals(other.getId());
	}
}
