package fr.yopsolo.formation.hibernate4All.heritage.concrete.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;

@MappedSuperclass
@Getter
@ToString
public abstract class AbstraiteProduction {

	@Column(nullable = false)
	private String name;

	private String description;

	public AbstraiteProduction setName(String pName) {
		name = pName;
		return this;
	}

	public AbstraiteProduction setDescription(String pDescription) {
		description = pDescription;
		return this;
	}

}
