package com.example.AssoSportive.document;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class Sport {

	@Field(value = "Jouer")
	List<String> jouer;
	@Field(value = "Arbitrer")
	List<String> arbitrer;
	@Field(value = "Entrainer")
	List<String> entrainer;
	
}
