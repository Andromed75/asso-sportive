package com.example.AssoSportive.document;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "Gymnases")
@Data
public class Gymnase {

	private String _id;
	@Field(value = "IdGymnase")
	private int idGymnase;
	@Field(value = "NomGymnase")
	private String nomGymnase;
	@Field(value = "Adresse")
	private String adresse;
	@Field(value = "Ville")
	private String ville;
	@Field(value = "Surface")
	private Integer surface;
	@Field(value = "Seances")
	private List<Seance> seances;
}
