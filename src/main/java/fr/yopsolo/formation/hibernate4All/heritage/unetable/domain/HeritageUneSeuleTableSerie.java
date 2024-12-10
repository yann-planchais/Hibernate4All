package fr.yopsolo.formation.hibernate4All.heritage.unetable.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString(callSuper = true)
@DiscriminatorValue(value = "serie")
public class HeritageUneSeuleTableSerie extends AbstraiteUneSeuleTableProduction {

	private int nombreSaisons;

}
