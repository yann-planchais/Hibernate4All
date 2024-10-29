/**
 * 
 */
package fr.yopsolo.formation.hibernate4All.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 */
@Getter
@Setter
@ToString
@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String name;
}
