package fr.yopsolo.formation.hibernate4All.heritage.unetable.domain;

import java.util.ArrayList;
import java.util.List;

import fr.yopsolo.formation.hibernate4All.domain.Certification;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@DiscriminatorValue(value = "film")
public class HeritageUneSeuleTableFilm extends AbstraiteUneSeuleTableProduction {

	// @Enumerated Non utile car converter
	private Certification certification;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HeritageUneSeuleTableReview> reviews = new ArrayList<HeritageUneSeuleTableReview>();

}
