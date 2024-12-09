package fr.yopsolo.formation.hibernate4All.heritage.seconde.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@ToString
public abstract class AbstraiteSecondeSolutionProduction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false)
	private String name;

	private String description;

	public AbstraiteSecondeSolutionProduction setName(String pName) {
		name = pName;
		return this;
	}

	public AbstraiteSecondeSolutionProduction setDescription(String pDescription) {
		description = pDescription;
		return this;
	}

}
