package com.EMSI.Voiture.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.EMSI.Voiture.model.voiture;
import com.EMSI.Voiture.repository.VoitureRepository;


@RestController 
public class VoitureController {
	
	@Autowired
	private VoitureRepository voitureRepository;
	
	@GetMapping("/voitures")
	private List<voiture> chercherClients(){
		
		return voitureRepository.findAll();		
	}
	
	@GetMapping("/voiture/{id}")
    public voiture chercherUnClients (@PathVariable Long id) throws Exception{

        return this.voitureRepository.findById(id).orElseThrow(()-> new Exception("Client inexistnt"));
        
	}
}
