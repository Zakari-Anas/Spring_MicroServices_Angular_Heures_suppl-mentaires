package com.supp.salle.controller;

import com.netflix.discovery.converters.Auto;
import com.supp.salle.model.Salle;
import com.supp.salle.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/Salle/")
@CrossOrigin(origins = "http://localhost:4200" ,allowedHeaders = "*")
public class SalleController {

    @Autowired
    private SalleRepository salleRepository;

    @PostMapping("/New")
    private Salle AjouterSalle (@RequestBody Salle salle){
       return  salleRepository.save(salle);
    }

    @GetMapping
    private List<Salle> GetSalles(){

        return salleRepository.findAll();
    }
    @GetMapping("{id}")
    private Salle getSalleById(@PathVariable Long id) throws Exception{

        return salleRepository.findById(id).orElseThrow(()->new Exception());
    }

    @PutMapping("{id}")
    private Salle UpdateSalle(@PathVariable Long id,@RequestBody Salle salle)throws Exception{

        Salle salle1=salleRepository.findById(id).orElseThrow(()->new Exception());
        salle1.setNom(salle.getNom());
        salle1.setLargeur(salle.getLargeur());
        salle1.setLongueur(salle.getLongueur());
        salle1.setHauteur(salle.getHauteur());

        return salleRepository.save(salle1);
    }

    @DeleteMapping("{id}")
    private void DeleteSalle(@PathVariable Long id ){
        salleRepository.deleteById(id);
    }
}
