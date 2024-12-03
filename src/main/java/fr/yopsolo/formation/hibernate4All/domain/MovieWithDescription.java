/**
 *
 */
package fr.yopsolo.formation.hibernate4All.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "MovieWithDescription") // non obligatoire
public class MovieWithDescription {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(unique = true)
	private String name;

	// @Transient => permet de ne pas mapper ce champ en BDD
	private String description;

	// @Enumerated Non utile car converter
	private Certification certification;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews = new ArrayList<Review>();

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Award> awards = new ArrayList<Award>();

	// Les objets Genre seron persisté lors d'un persist ou d'un merge du movie
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	// Definit les noms de la table et de ses attributs
	@JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<Genre> genres = new HashSet<>();

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MovieActor> moviesActors = new ArrayList<>();

	public void addActor(Actor pActor, String pCharacter) {
		MovieActor movieActor = new MovieActor(this, pActor).setCharacter(pCharacter);
		moviesActors.add(movieActor);
		pActor.getMoviesActors().add(movieActor);
	}

	public void removeActor(Actor pActor) {

		for (Iterator<MovieActor> iteMmovieActor = moviesActors.iterator(); iteMmovieActor.hasNext();) {
			MovieActor current = iteMmovieActor.next();
			if (current.getMovie().equals(this) && current.getActor().equals(pActor)) {
				iteMmovieActor.remove();
				current.getActor().getMoviesActors().remove(current);
				current.setActor(null);
				current.setMovie(null);
			}
		}

		MovieActor movieActor = new MovieActor(this, pActor);
		moviesActors.remove(movieActor);
		pActor.getMoviesActors().remove(movieActor);
	}

	public void updateActor(Actor pActor, String pCharacter) {
		moviesActors.stream().forEach(movieActor -> {
			if (this.equals(movieActor.getMovie()) && movieActor.getActor().equals(pActor)) {
				movieActor.setCharacter(pCharacter);
			}
		});
	}

	public MovieWithDescription addGenre(Genre pGenreToAdd) {
		if (pGenreToAdd != null) {
			this.genres.add(pGenreToAdd);
			pGenreToAdd.getMovies().add(this);
		}
		return this;
	}

	public MovieWithDescription removeGenre(Genre pGenreToSuppress) {
		if (pGenreToSuppress != null && this.genres.contains(pGenreToSuppress)) {
			genres.remove(pGenreToSuppress);
			pGenreToSuppress.getMovies().remove(this);
		}

		return this;
	}

	public Set<Genre> getGenres() {
		return Collections.unmodifiableSet(this.genres);
	}

	public MovieWithDescription addReview(Review pReview) {
		if (pReview != null) {
			this.reviews.add(pReview);
			pReview.setMovie(this);
		}
		return this;
	}

	public MovieWithDescription removeReview(Review pReview) {
		if (pReview != null && this.reviews.contains(pReview)) {
			reviews.remove(pReview);
			pReview.setMovie(null);
		}

		return this;
	}

	public MovieWithDescription addAward(Award pAward) {
		if (pAward != null) {
			this.awards.add(pAward);
			pAward.setMovie(this);
		}
		return this;
	}

	public MovieWithDescription removeAward(Award pAward) {
		if (pAward != null && this.awards.contains(pAward)) {
			awards.remove(pAward);
			pAward.setMovie(null);
		}

		return this;
	}

	public List<Award> getAwards() {
		return Collections.unmodifiableList(this.awards);
	}

	// le return this sur les setter est une utilisation "fluent" : <br />
	// ça permet l'enchainement des setters
	public MovieWithDescription setIdFluent(Long pId) {
		id = pId;
		return this;
	}

	public MovieWithDescription setNameFluent(String pName) {
		name = pName;
		return this;
	}

	public MovieWithDescription setDescriptionFluent(String pDescription) {
		description = pDescription;
		return this;
	}

	public MovieWithDescription setCertificationFluent(Certification pCertification) {
		certification = pCertification;
		return this;
	}

	@Override
	public int hashCode() {
		// la méthode par défaut générée ne convient pas pour les entités bdd lors de
		// l'utilisation de Set
		// une fois persisté, l'objet va changer de hashcode (du à l'id) et on ne
		// retrouvera pas l'objet
		// return Objects.hash(certification, name, description, id);
		return 32;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MovieWithDescription)) {
			return false;
		}

		MovieWithDescription other = (MovieWithDescription) obj;
		if (id == null && other.getId() == null) {
			return Objects.equals(name, other.getName()) && Objects.equals(description, other.getDescription())
					&& certification == other.certification;
		}
		return id != null && Objects.equals(id, other.getId());

	}

}
