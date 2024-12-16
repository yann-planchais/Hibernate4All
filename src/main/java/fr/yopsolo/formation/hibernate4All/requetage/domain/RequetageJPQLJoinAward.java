package fr.yopsolo.formation.hibernate4All.requetage.domain;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class RequetageJPQLJoinAward {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;

	private Integer annee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_id") // Non obligatoire. Indique la clé étrangère
	private RequetageJPQLJoinMovie movie;

	@Override
	public int hashCode() {
		return 33;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof RequetageJPQLJoinAward)) {
			return false;
		}

		RequetageJPQLJoinAward other = (RequetageJPQLJoinAward) obj;
		if (id == null && other.getId() == null) {
			return Objects.equals(name, other.getName()) && Objects.equals(annee, other.getAnnee());
		} else {
			return id != null && Objects.equals(id, other.getId());
		}
	}

	public RequetageJPQLJoinAward setName(String pName) {
		name = pName;
		return this;
	}

	public RequetageJPQLJoinAward setAnnee(Integer pAnnee) {
		annee = pAnnee;
		return this;
	}

	public RequetageJPQLJoinAward setMovie(RequetageJPQLJoinMovie pMovie) {
		movie = pMovie;
		return this;
	}

}
