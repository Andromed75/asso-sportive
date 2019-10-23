package com.example.AssoSportive.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.AssoSportive.document.Gymnase;
import com.example.AssoSportive.document.Seance;
import com.example.AssoSportive.repo.GymnaseRepo;

@RestController
@RequestMapping("/gymnases")
public class GymnaseController {

	@Autowired
	private GymnaseRepo gymnaseRepo;

	@Autowired
	private MongoTemplate mongoTemplate;

	@GetMapping()
	public ResponseEntity<List<Gymnase>> getAll() {
		List<Gymnase> gymList = gymnaseRepo.findAll();
		return ResponseEntity.ok(gymList);
	}

	@GetMapping("/noms")
	public ResponseEntity<List<String>> getNoms() {
		List<Gymnase> gymList = gymnaseRepo.findAll();
		List<String> listNomGym = new ArrayList<>();
		for (Gymnase x : gymList) {
			listNomGym.add(x.getNomGymnase());
		}
		return ResponseEntity.ok(listNomGym);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Gymnase> getGymanseById(@PathVariable("id") String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		List<Gymnase> users = mongoTemplate.find(query, Gymnase.class);
		return ResponseEntity.ok(users.stream().findFirst().get());
	}

	@GetMapping("/nom/{nom}")
	public ResponseEntity<List<Gymnase>> getGymanseByNom(@PathVariable("nom") String nomGymnase) {
		Query query = new Query();
		String prams = "^" + nomGymnase;
		query.addCriteria(Criteria.where("nomGymnase").regex(prams));
		List<Gymnase> users = mongoTemplate.find(query, Gymnase.class);
		return ResponseEntity.ok(users);
	}

	@GetMapping("/surface-min-max/{min}&{max}")
	public ResponseEntity<List<Gymnase>> getSurfaceWithParams(
			@RequestParam(required = false, value = "min") Integer min,
			@RequestParam(required = false, value = "max") Integer max) {
		List<Gymnase> users = addMinMax(min, max);
		return ResponseEntity.ok(users);
	}

	private List<Gymnase> addMinMax(Integer min, Integer max) {
		Query query = new Query();
		if (min == null && max == null) {
			throw new Error("MIN ET MAX NON RENSEIGNÉS");
		}
		else if (min == null) { 
			query.addCriteria(Criteria.where("surface").lt(max));
		}
		else if (max == null) {
			query.addCriteria(Criteria.where("surface").gt(min));
		}
		 else {
			query.addCriteria(Criteria.where("surface").lt(max).gt(min));
		}
		List<Gymnase> users = mongoTemplate.find(query, Gymnase.class);
		return users;
	}

	@GetMapping("/surface")
	public ResponseEntity<List<Integer>> getSurfaceMoyenne() {
		List<Gymnase> gymList = gymnaseRepo.findAll();
		List<Integer> listNomGym = new ArrayList<>();
		for (Gymnase x : gymList) {
			listNomGym.add(x.getSurface());
		}
		return ResponseEntity.ok(listNomGym);
	}
	
	@GetMapping("/seances/{id}")
	public ResponseEntity<List<Seance>> getSeances(@PathVariable("id") Integer idGymnase) {
		Query query = new Query();
		query.addCriteria(Criteria.where("idGymnase").is(idGymnase));
		Gymnase gymnase = mongoTemplate.find(query, Gymnase.class).stream().findFirst().get();	
		List<Seance> seancesList = gymnase.getSeances();
		return ResponseEntity.ok(seancesList);
	}
	
	@GetMapping("/seances-entraineur/{id}")
	public ResponseEntity<List<Seance>> getSeancesByEntraineurId(@PathVariable("id") Integer entraineurId) {
	
		List<Gymnase> gymnase = gymnaseRepo.findAll();
		List<Seance> seanceList = new ArrayList<>();
		
		for(Gymnase g : gymnase) {
			if(g.getSeances() != null) {
				for(Seance s : g.getSeances()) {
					if(entraineurId.equals(s.getIdSportifEtraineur())) {
						seanceList.add(s);
					}
				}
			}
			
		}
		
		return ResponseEntity.ok(seanceList);
	}
	
	
	
	
	
	

}
