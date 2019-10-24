package com.example.AssoSportive.document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.lang.Nullable;

import lombok.Data;

@Data
@Document(collection = "Sportifs")
public class Sportif{
	
	@Field("_id")
	private String _id;
	@Field(value = "IdSportif")
	private int idSportif;
	@Field(value = "Nom")
	private String nom;
	@Field(value = "Prenom")
	private String prenom;
	@Field(value = "Sexe")
	private String sexe;
	@Field(value = "Age")
	private int age;
	@Field(value = "IdSportifConseiller")
	@Nullable
	private int idSportifConseiller;
	@Field(value = "Sports")
	private Sport sport;
	
}
