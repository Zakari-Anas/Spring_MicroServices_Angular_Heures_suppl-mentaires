package com.supp.Etudiant.Controller;

import com.supp.Etudiant.Repository.EtudiantRepository;
import com.supp.Etudiant.Service.EtudiantService;
import com.supp.Etudiant.model.Etudiant;
import com.supp.Etudiant.model.EtudiantDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Etudiants/")
public class EtudiantController {

    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    private EtudiantService etudiantService;


    @PostMapping("/New")
    public Etudiant CreateGroupe(@RequestBody Etudiant e) {
        return etudiantRepository.save(e);

    }

    @GetMapping
    public List<EtudiantDetails> GetAllEtudiants(){
        return etudiantService.findAll();
    }

    @GetMapping("/{id}")
    public EtudiantDetails getGroupById(@PathVariable Long id) throws Exception{

        return etudiantService.findEtudiantByID(id);
    }

}
