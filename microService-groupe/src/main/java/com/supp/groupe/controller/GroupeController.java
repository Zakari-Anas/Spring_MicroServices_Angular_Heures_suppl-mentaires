package com.supp.groupe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.supp.groupe.model.Groupe;
import com.supp.groupe.repository.GroupeRepository;

import java.util.List;

@RestController
@RequestMapping("/api/Groupe/")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8888"}, allowedHeaders = "*")

public class GroupeController {

	@Autowired
	private GroupeRepository groupeRepository;



	@PostMapping("/New")
	public Groupe CreateGroupe(@RequestBody Groupe g) {
		return groupeRepository.save(g);

	}
	@GetMapping
	public List<Groupe> GetAllGroups(){
		return groupeRepository.findAll();
	}

	@GetMapping("/{id}")
	public Groupe getGroupById(@PathVariable Long id) throws Exception{

		return groupeRepository.findById(id).orElseThrow(() -> new Exception("Group Invalid"));
	}

	@PutMapping("/{id}")
	public Groupe UpdateGroup( @PathVariable Long id , @RequestBody Groupe groupe) throws Exception {

		Groupe findGroup=groupeRepository.findById(id).orElseThrow(() -> new Exception("Group Invalid"));
		if (findGroup != null){
			findGroup.setName(groupe.getName());
			findGroup.setCount(groupe.getCount());

		}
		groupeRepository.save(findGroup);
		return  findGroup;
	}
}
