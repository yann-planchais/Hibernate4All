package fr.yopsolo.formation.hibernate4All.heritage.concrete.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class HeritageSerie extends Watchable {

	private int nombreSaison;

}
