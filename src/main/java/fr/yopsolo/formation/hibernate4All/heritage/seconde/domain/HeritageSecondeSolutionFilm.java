package fr.yopsolo.formation.hibernate4All.heritage.seconde.domain;

import java.util.ArrayList;
import java.util.List;

import fr.yopsolo.formation.hibernate4All.domain.Certification;
import jakarta.persistence.CascadeType;
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
public class HeritageSecondeSolutionFilm extends AbstraiteSecondeSolutionProduction {

	// @Enumerated Non utile car converter
	private Certification certification;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SecondReview> reviews = new ArrayList<SecondReview>();

}
