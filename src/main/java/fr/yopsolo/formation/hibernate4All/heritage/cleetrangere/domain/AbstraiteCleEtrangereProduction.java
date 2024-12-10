package fr.yopsolo.formation.hibernate4All.heritage.cleetrangere.domain;

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
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@ToString
public abstract class AbstraiteCleEtrangereProduction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false)
	private String name;

	private String description;

	public AbstraiteCleEtrangereProduction setName(String pName) {
		name = pName;
		return this;
	}

	public AbstraiteCleEtrangereProduction setDescription(String pDescription) {
		description = pDescription;
		return this;
	}

}
