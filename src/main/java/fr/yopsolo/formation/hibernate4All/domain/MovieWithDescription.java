/**
 *
 */
package fr.yopsolo.formation.hibernate4All.domain;

import jakarta.persistence.Column;
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
@Table(name = "MovieWithDescription") // non obligatoire
public class MovieWithDescription {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(unique = true)
	private String name;

	// @Transient => permet de ne pas mapper ce champ en BDD
	private String description;

	// @Enumerated Non utile car converter
	private Certification certification;

	// le return this sur les setter est une utilisation "fluent" : <br />
	// ça permet l'enchainement des setters
	public MovieWithDescription setIdFluent(Long pId) {
		id = pId;
		return this;
	}

	public MovieWithDescription setNameFluent(String pName) {
		name = pName;
		return this;
	}

	public MovieWithDescription setDescriptionFluent(String pDescription) {
		description = pDescription;
		return this;
	}

	public MovieWithDescription setCertificationFluent(Certification pCertification) {
		certification = pCertification;
		return this;
	}

}
