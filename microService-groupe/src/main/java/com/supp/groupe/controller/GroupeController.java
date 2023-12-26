package com.supp.groupe.controller;

import com.supp.groupe.model.GroupeDetails;
import com.supp.groupe.service.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.supp.groupe.model.Groupe;
import com.supp.groupe.repository.GroupeRepository;

import java.util.List;

@RestController
@RequestMapping("/api/Groupe/")
public class GroupeController {

	@Autowired
	private GroupeRepository groupeRepository;

	@Autowired
	private GroupeService groupeService;


	@PostMapping("/New")
	public Groupe CreateGroupe(@RequestBody Groupe g) {
		return groupeRepository.save(g);

	}
	@GetMapping
	public List<GroupeDetails> GetAllGroups(){
		return groupeService.findAll();
	}

	@GetMapping("/{id}")
	public GroupeDetails getGroupById(@PathVariable Long id) throws Exception{

		return groupeService.findGroupByID(id);
	}
}
