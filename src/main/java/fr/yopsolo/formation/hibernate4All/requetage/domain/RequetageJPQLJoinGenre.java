package fr.yopsolo.formation.hibernate4All.requetage.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;

@Entity
@Getter
public class RequetageJPQLJoinGenre {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "requetageJPQLJoinGenres")
	private Set<RequetageJPQLJoinMovie> movies = new HashSet<>();

	public RequetageJPQLJoinGenre setName(String pName) {
		name = pName;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof RequetageJPQLJoinGenre)) {
			return false;
		}

		RequetageJPQLJoinGenre other = (RequetageJPQLJoinGenre) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Genre [name=" + name + "]";
	}
}
