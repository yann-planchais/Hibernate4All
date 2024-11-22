package fr.yopsolo.formation.hibernate4All.domain;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie_details")
public class MovieDetails {


	@Id
	private Long id;
	
	/*
	 * Représente les détails d'un film
	 */
	@Column(length = 4000)
	private String plot;
	
	@OneToOne
	@MapsId // permet de dire que la clé primaire de movie est aussi la clé primaire de cette table
	private MovieWithDescription movieWithDescription;

	public Long getId() {
		return id;
	}

	public MovieDetails setId(Long id) {
		this.id = id;
		return this;
	}

	public String getPlot() {
		return plot;
	}

	public MovieDetails setPlot(String plot) {
		this.plot = plot;
		return this;
	}

	public MovieWithDescription getMovieWithDescription() {
		return movieWithDescription;
	}

	public MovieDetails setMovieWithDescription(MovieWithDescription movieWithDescription) {
		this.movieWithDescription = movieWithDescription;
		return this;
	}
	
	@Override
	public int hashCode() {
		return 58;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof MovieDetails)) {
			return false;
		}
		
		MovieDetails other = (MovieDetails) obj;
		if(id==null && other.getId() == null) {
		return Objects.equals(plot, other.plot) ;
		} else {
			return id != null && Objects.equals(id, other.id);
		}
	}
	
	
}
