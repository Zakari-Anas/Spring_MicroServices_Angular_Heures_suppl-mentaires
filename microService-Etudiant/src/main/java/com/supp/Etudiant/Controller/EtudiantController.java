package com.supp.Etudiant.Controller;

import com.supp.Etudiant.Repository.EtudiantRepository;
import com.supp.Etudiant.Service.EtudiantService;
import com.supp.Etudiant.model.Etudiant;
import com.supp.Etudiant.model.EtudiantDetails;
import com.supp.Etudiant.model.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Etudiants/")
@CrossOrigin(origins = "http://localhost:4200" ,allowedHeaders = "*")
public class EtudiantController {

    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    private EtudiantService etudiantService;


    @PostMapping("/New")
    public Etudiant CreateGroupe(@RequestBody Etudiant e) {
        return etudiantRepository.save(e);

    }
    @GetMapping("/all")
    public List<Etudiant> getAllE(){
        return etudiantService.getAllEtudiant();
    }

    @DeleteMapping("{id}")
    public void deleteEtudiant(@PathVariable Long id)
    {
        etudiantRepository.deleteById(id);
    }
    @GetMapping
    public List<EtudiantDetails> GetAllEtudiants(){
        return etudiantService.findAll();
    }

    @GetMapping("/{id}")
    public EtudiantDetails getEtudiantById(@PathVariable Long id) throws Exception{

        return etudiantService.findEtudiantByID(id);
    }

    @PutMapping("/update/{id}")
    public Etudiant updateEtudiant(@RequestBody Etudiant etudiant,@PathVariable Long id) throws Exception{

        return etudiantService.UpdateEtudiant(etudiant,id);
    }

    @GetMapping("/Groupe/{id}")
    public List<EtudiantDetails> getEtudiantByGroupe(@PathVariable Long id){
        return etudiantService.getEtudiantByGroupe(id);
    }

    @GetMapping("/Groupes")
    public List<Groupe> getAllGroupes(){
        return etudiantService.allGroups();
    }

}
