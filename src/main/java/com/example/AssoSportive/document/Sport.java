package com.example.AssoSportive.document;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class Sport {

	@Field(value = "Jouer")
	String[] jouer;
	@Field(value = "Arbitrer")
	String[] arbitrer;
	@Field(value = "Entrainer")
	String[] entrainer;
	
}
