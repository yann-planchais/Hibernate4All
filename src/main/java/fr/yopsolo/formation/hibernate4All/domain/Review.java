package fr.yopsolo.formation.hibernate4All.domain;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String author;
	
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="movie_id")  // Non obligatoire. Indique la clé étrangère
	private MovieWithDescription movie;
	
	@Override
	public int hashCode() {
		return 32;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Review)) {
			return false;
		}
		
		Review other = (Review) obj;
		if(id==null && other.getId() == null) {
		return Objects.equals(author, other.author) && Objects.equals(content, other.content)
				;
		} else {
			return id != null && Objects.equals(id, other.id);
		}
	}
	
	
}
