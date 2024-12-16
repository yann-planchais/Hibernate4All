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

@Entity
@Getter

public class RequetageJPQLJoinReview {

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
	private RequetageJPQLJoinMovie movie;

	public RequetageJPQLJoinReview setRating(Integer pRating) {
		rating = pRating;
		return this;
	}

	public RequetageJPQLJoinReview setAuthor(String pAuthor) {
		author = pAuthor;
		return this;
	}

	public RequetageJPQLJoinReview setContent(String pContent) {
		content = pContent;
		return this;
	}

	public RequetageJPQLJoinReview setMovie(RequetageJPQLJoinMovie pMovie) {
		movie = pMovie;
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
		if (!(obj instanceof RequetageJPQLJoinReview)) {
			return false;
		}

		RequetageJPQLJoinReview other = (RequetageJPQLJoinReview) obj;
		if (id == null && other.getId() == null) {
			return Objects.equals(author, other.getAuthor()) && Objects.equals(content, other.getContent())
					&& Objects.equals(rating, other.getRating());
		} else {
			return id != null && Objects.equals(id, other.getId());
		}
	}

}
