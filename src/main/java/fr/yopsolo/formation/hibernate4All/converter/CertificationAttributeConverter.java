package fr.yopsolo.formation.hibernate4All.converter;

import java.util.stream.Stream;

import fr.yopsolo.formation.hibernate4All.domain.Certification;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true) // oblige la convertion à chaque fois qu'il rencontre un objet du domaine
public class CertificationAttributeConverter implements AttributeConverter<Certification, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Certification pAttribute) {

		return pAttribute != null ? pAttribute.getCle() : null;
	}

	@Override
	public Certification convertToEntityAttribute(Integer pDbData) {
		return Stream.of(Certification.values()).filter(certif -> certif.getCle().equals(pDbData)).findFirst()
				.orElse(null);
	}

}
