package com.example.AssoSportive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AssoSportive.document.Sportif;
import com.example.AssoSportive.repo.SportifsRepo;

@RequestMapping("/sportifs")
@RestController
public class SportifsController {
	
	@Autowired
	private SportifsRepo sportifRepo;
	
	@GetMapping
	public ResponseEntity<List<Sportif>> getAll(){
		List<Sportif> sportifList = sportifRepo.findAll();
		return ResponseEntity.ok(sportifList);
	}

}
