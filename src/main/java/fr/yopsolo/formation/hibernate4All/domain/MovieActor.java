package fr.yopsolo.formation.hibernate4All.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "movie_actor")
@Getter
@ToString
public class MovieActor {

	@Embeddable
	@Getter
	@Setter
	@ToString
	public static class MovieActorId implements Serializable {
		/**
		 *
		 */
		private static final long serialVersionUID = 3233452767933115906L;
		@Column(name = "movie_id")
		private Long movieId;

		@Column(name = "actor_id")
		private Long actorId;

		private MovieActorId() {
			// Necessaire pour hibernate
		}

		public MovieActorId(Long pMovieId, Long pActorId) {
			this.movieId = pMovieId;
			this.actorId = pActorId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(actorId, movieId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof MovieActorId)) {
				return false;
			}
			MovieActorId other = (MovieActorId) obj;
			return Objects.equals(actorId, other.actorId) && Objects.equals(movieId, other.movieId);
		}

	}

	@EmbeddedId
	private MovieActorId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("movieId")
	private MovieWithDescription movie;

	@MapsId("actorId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Actor actor;

	private String character;

	private MovieActor() {

	}

	public MovieActor(MovieWithDescription pMovieWithDescription, Actor pActor) {
		this.movie = pMovieWithDescription;
		this.actor = pActor;
		this.id = new MovieActorId(movie.getId(), actor.getId());
	}

	public MovieActor setId(MovieActorId pId) {
		id = pId;
		return this;
	}

	public MovieActor setMovie(MovieWithDescription pMovie) {
		movie = pMovie;
		return this;
	}

	public MovieActor setActor(Actor pActor) {
		actor = pActor;
		return this;
	}

	public MovieActor setCharacter(String pCharacter) {
		character = pCharacter;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actor, movie);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MovieActor)) {
			return false;
		}
		MovieActor other = (MovieActor) obj;
		return Objects.equals(actor, other.actor) && Objects.equals(movie, other.movie);
	}
}
