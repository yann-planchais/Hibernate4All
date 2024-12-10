package fr.yopsolo.formation.hibernate4All.requetage.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum RequetageCertification {

	TOUS_PUBLIC(1, "Tout public"), INTERDIT_MOINS_12(2, "-12 ans "), INTERDIT_MOINS_16(3, "-16 ans "),
	INTERDIT_MOINS_18(4, "-18 ans ");

	private Integer cle;

	private String description;

	private RequetageCertification(Integer pCle, String pDescription) {
		cle = pCle;
		description = pDescription;
	}
}
