package fr.yopsolo.formation.hibernate4All.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String name;

	
	@ManyToMany(mappedBy = "genres")
	private Set<MovieWithDescription> movies = new HashSet<>();
	
	@Override
	public int hashCode() {
		return Objects.hash( name);
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Genre)) {
			return false;
		}

		Genre other = (Genre) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Genre [name=" + name + "]";
	}
}
