package com.example.AssoSportive.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.AssoSportive.document.Gymnase;
import com.example.AssoSportive.document.Sport;
import com.example.AssoSportive.document.Sportif;
import com.example.AssoSportive.repo.SportifsRepo;

@CrossOrigin
@RequestMapping("/sportifs")
@RestController
public class SportifsController {

	@Autowired
	private SportifsRepo sportifRepo;

	@Autowired
	private MongoTemplate mongoTemplate;

	@GetMapping
	public ResponseEntity<List<Sportif>> getAll() {
		List<Sportif> sportifList = sportifRepo.findAll();
		return ResponseEntity.ok(sportifList);
	}

	@GetMapping("/noms/{id}")
	public ResponseEntity<Sportif> getNoms(@PathVariable("id") String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		List<Sportif> users = mongoTemplate.find(query, Sportif.class);
		return ResponseEntity.ok(users.stream().findFirst().get());
	}

	@GetMapping("/nom")
	public ResponseEntity<List<Sportif>> getSportifsByNom(@RequestParam(required = false, value = "prenom") String prenom,
			@RequestParam(required = false, value = "nom") String nom) {
		Query query = new Query();
		String prams = "^" + nom;
		String prams2 = "^" + prenom;
		if(prenom==null) {
			query.addCriteria(Criteria.where("nom").regex(prams));
		}
		else if(nom==null) {
			query.addCriteria(Criteria.where("prenom").regex(prams2));
		}
		else {
			query.addCriteria(Criteria.where("nom").regex(prams).orOperator(Criteria.where("prenom").regex(prams2)));
		}
		List<Sportif> users = mongoTemplate.find(query, Sportif.class);
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/jouer/{id}")
	public ResponseEntity<List<String>> getSportifsJouer(@PathVariable("id")String id){
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Sportif sportif = mongoTemplate.find(query, Sportif.class).stream().findFirst().get();
		return ResponseEntity.ok(sportif.getSport().getJouer());
	}
	
	@GetMapping("/arbitrer/{id}")
	public ResponseEntity<List<String>> getSportifsArbitrer(@PathVariable("id")String id){
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Sportif sportif = mongoTemplate.find(query, Sportif.class).stream().findFirst().get();
		return ResponseEntity.ok(sportif.getSport().getArbitrer());
	}
	
	@GetMapping("/entrainer/{id}")
	public ResponseEntity<List<String>> getSportifsEntrainer(@PathVariable("id")String id){
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Sportif sportif = mongoTemplate.find(query, Sportif.class).stream().findFirst().get();
		return ResponseEntity.ok(sportif.getSport().getEntrainer());
	}
	
	@GetMapping("/sports/{id}")
	public ResponseEntity<Sport> getSportifsSportTotal(@PathVariable("id")String id){
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Sportif sportif = mongoTemplate.find(query, Sportif.class).stream().findFirst().get();
		return ResponseEntity.ok(sportif.getSport());
	}
	
	@GetMapping("/sportifs-by-sport/{sport}")
	public ResponseEntity<List<Sportif>> getSportifBySport(@PathVariable("sport")String sport){
		List<Sportif> sportifList = sportifRepo.findAll();
		List<Sportif> outList = new ArrayList<>();
		for(Sportif s : sportifList) {
			if(s.getSport() != null && s.getSport().getJouer() != null) {
				if(s.getSport().getJouer().contains(sport)) {
					outList.add(s);
				}
			}
			
		}
		return ResponseEntity.ok(outList);
	}
	
	
	
	
	
	

}
