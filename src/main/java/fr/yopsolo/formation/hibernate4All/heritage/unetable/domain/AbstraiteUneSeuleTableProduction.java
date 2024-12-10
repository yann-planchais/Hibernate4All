package fr.yopsolo.formation.hibernate4All.heritage.unetable.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "bd_type")
@Getter
@ToString
public abstract class AbstraiteUneSeuleTableProduction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false)
	private String name;

	private String description;

	public AbstraiteUneSeuleTableProduction setName(String pName) {
		name = pName;
		return this;
	}

	public AbstraiteUneSeuleTableProduction setDescription(String pDescription) {
		description = pDescription;
		return this;
	}

}
