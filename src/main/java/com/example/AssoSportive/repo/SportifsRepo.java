package com.example.AssoSportive.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.AssoSportive.document.Sportif;

public interface SportifsRepo extends MongoRepository<Sportif, String>{
	
	
	@Query("{ '_id' : ?0 }")
	Sportif findOne(String string);
	
}
