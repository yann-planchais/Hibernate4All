package fr.yopsolo.formation.hibernate4All.heritage.cleetrangere.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString(callSuper = true)
public class HeritageCleEtrangereSerie extends AbstraiteCleEtrangereProduction {

	private int nombreSaisons;

}
