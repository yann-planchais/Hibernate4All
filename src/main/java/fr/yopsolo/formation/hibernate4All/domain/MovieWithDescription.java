/**
 *
 */
package fr.yopsolo.formation.hibernate4All.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	
	public MovieWithDescription addReview(Review pReview) {
		if(pReview != null) {
			this.reviews.add(pReview);
			pReview.setMovie(this);
		}
		return this;
	}
	
	public MovieWithDescription removeReview(Review pReview) {
		if(pReview != null && this.reviews.contains(pReview)) {
			reviews.remove(pReview);
			pReview.setMovie(null);
		}
		
		return this;
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
		return 31;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieWithDescription other = (MovieWithDescription) obj;
		return certification == other.certification && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(reviews, other.reviews);
	}

}
