package fr.yopsolo.formation.hibernate4All.heritage.concrete.domain;

import fr.yopsolo.formation.hibernate4All.domain.Certification;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "HeritageFilm")
public class HeritageFilm extends Watchable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	// @Enumerated Non utile car converter
	private Certification certification;

}
