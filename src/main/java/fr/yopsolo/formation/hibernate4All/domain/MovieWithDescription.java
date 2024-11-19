/**
 *
 */
package fr.yopsolo.formation.hibernate4All.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "MovieWithDescription") // non obligatoire
public class MovieWithDescription {

	public enum Certification {
		TOUS_PUBLIC, INTERDIT_MOINS_12, INTERDIT_MOINS_16, INTERDIT_MOINS_18
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(unique = true)
	private String name;

	// @Transient => permet de ne pas mapper ce champ en BDD
	private String description;

	@Enumerated
	private Certification certificationStockeEnInteger;

	@Enumerated(EnumType.STRING)
	private Certification certificationStockeEnString;

}
