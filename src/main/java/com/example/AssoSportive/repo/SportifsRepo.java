package com.example.AssoSportive.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.AssoSportive.document.Sportif;

public interface SportifsRepo extends MongoRepository<Sportif, String>{
	
}
