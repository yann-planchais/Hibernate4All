package fr.yopsolo.formation.hibernate4All.requetage.domain;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RequetageReview {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String author;

	private String content;

	@Min(value = 0)
	@Max(value = 10, message = "Valeur trop haute")
	private Integer rating;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_id") // Non obligatoire. Indique la clé étrangère
	private RequetageMovieWithDescription movie;

	public RequetageReview setRating(Integer pRating) {
		rating = pRating;
		return this;
	}

	@Override
	public int hashCode() {
		return 32;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof RequetageReview)) {
			return false;
		}

		RequetageReview other = (RequetageReview) obj;
		if (id == null && other.getId() == null) {
			return Objects.equals(author, other.getAuthor()) && Objects.equals(content, other.getContent())
					&& Objects.equals(rating, other.getRating());
		} else {
			return id != null && Objects.equals(id, other.getId());
		}
	}

}
