package com.example.AssoSportive.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.AssoSportive.document.Gymnase;

public interface GymnaseRepo extends MongoRepository<Gymnase, String>{

}
