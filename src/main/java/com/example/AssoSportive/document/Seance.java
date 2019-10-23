package com.example.AssoSportive.document;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class Seance {

	@Field(value = "IdSportifEntraineur")
	private Integer idSportifEtraineur;
	@Field(value = "Jour")
	private String jour;
	@Field(value = "Horaire")
	private double horaire;
	@Field(value = "Duree")
	private int duree;
	@Field(value = "Libelle")
	private String libelle;
}
