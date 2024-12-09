package fr.yopsolo.formation.hibernate4All.heritage.seconde.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString(callSuper = true)
public class HeritageSecondeSolutionSerie extends AbstraiteSecondeSolutionProduction {

	private int nombreSaisons;

}
