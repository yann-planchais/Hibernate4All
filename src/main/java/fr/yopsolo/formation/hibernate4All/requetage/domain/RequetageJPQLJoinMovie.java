/**
 *
 */
package fr.yopsolo.formation.hibernate4All.requetage.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
@Table(name = "RequetageJPQLJoinMovie") // non obligatoire
public class RequetageJPQLJoinMovie {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(unique = true)
	private String name;

	// @Transient => permet de ne pas mapper ce champ en BDD
	private String description;

	// @Enumerated Non utile car converter
	private RequetageCertification certification;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RequetageJPQLJoinReview> requetageJPQLJoinReviews = new ArrayList<>();

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RequetageJPQLJoinAward> awards = new ArrayList<>();

	// Les objets Genre seron persisté lors d'un persist ou d'un merge du movie
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	// Definit les noms de la table et de ses attributs
	@JoinTable(name = "requetagejpqljoinmovie_requetagejpqljoingenre", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<RequetageJPQLJoinGenre> requetageJPQLJoinGenres = new HashSet<>();

	/*
	 *
	 * Accesseurs
	 *
	 */

	public RequetageJPQLJoinMovie addGenre(RequetageJPQLJoinGenre pGenreToAdd) {
		if (pGenreToAdd != null) {
			this.requetageJPQLJoinGenres.add(pGenreToAdd);
			pGenreToAdd.getMovies().add(this);
		}
		return this;
	}

	public RequetageJPQLJoinMovie removeGenre(RequetageJPQLJoinGenre pGenreToSuppress) {
		if (pGenreToSuppress != null && this.requetageJPQLJoinGenres.contains(pGenreToSuppress)) {
			requetageJPQLJoinGenres.remove(pGenreToSuppress);
			pGenreToSuppress.getMovies().remove(this);
		}

		return this;
	}

	public Set<RequetageJPQLJoinGenre> getGenres() {
		return Collections.unmodifiableSet(this.requetageJPQLJoinGenres);
	}

	public RequetageJPQLJoinMovie addReview(RequetageJPQLJoinReview pReview) {
		if (pReview != null) {
			this.requetageJPQLJoinReviews.add(pReview);
			pReview.setMovie(this);
		}
		return this;
	}

	public RequetageJPQLJoinMovie removeReview(RequetageJPQLJoinReview pReview) {
		if (pReview != null && this.requetageJPQLJoinReviews.contains(pReview)) {
			requetageJPQLJoinReviews.remove(pReview);
			pReview.setMovie(null);
		}

		return this;
	}

	public RequetageJPQLJoinMovie addAward(RequetageJPQLJoinAward pAward) {
		if (pAward != null) {
			this.awards.add(pAward);
			pAward.setMovie(this);
		}
		return this;
	}

	public RequetageJPQLJoinMovie removeAward(RequetageJPQLJoinAward pAward) {
		if (pAward != null && this.awards.contains(pAward)) {
			awards.remove(pAward);
			pAward.setMovie(null);
		}

		return this;
	}

	public List<RequetageJPQLJoinAward> getAwards() {
		return Collections.unmodifiableList(this.awards);
	}

	// le return this sur les setter est une utilisation "fluent" : <br />
	// ça permet l'enchainement des setters
	public RequetageJPQLJoinMovie setIdFluent(Long pId) {
		id = pId;
		return this;
	}

	public RequetageJPQLJoinMovie setNameFluent(String pName) {
		name = pName;
		return this;
	}

	public RequetageJPQLJoinMovie setDescriptionFluent(String pDescription) {
		description = pDescription;
		return this;
	}

	public RequetageJPQLJoinMovie setCertificationFluent(RequetageCertification pCertification) {
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
		if (!(obj instanceof RequetageJPQLJoinMovie)) {
			return false;
		}

		RequetageJPQLJoinMovie other = (RequetageJPQLJoinMovie) obj;
		if (id == null && other.getId() == null) {
			return Objects.equals(name, other.getName()) && Objects.equals(description, other.getDescription())
					&& certification == other.certification;
		}
		return id != null && Objects.equals(id, other.getId());

	}

}
