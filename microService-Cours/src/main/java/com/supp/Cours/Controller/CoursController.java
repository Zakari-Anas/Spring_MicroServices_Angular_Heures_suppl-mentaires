package com.supp.Cours.Controller;

import com.supp.Cours.Service.CoursService;
import com.supp.Cours.model.Cours;
import com.supp.Cours.model.CoursDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Cours/")
@CrossOrigin(origins = "http://localhost:4200" ,allowedHeaders = "*")
public class CoursController {

    @Autowired
    private CoursService coursService;

    @PostMapping("/New")
    public Cours Ajoutercour (@RequestBody Cours cour){

        return coursService.addCours(cour) ;
    }

    @GetMapping()
    public List<CoursDetails> findAll(){

        return coursService.findAll();

    }

    @GetMapping("{id}")
    public CoursDetails findById(@PathVariable Long id ) throws Exception {

        return coursService.getCoursById(id);

    }

    @DeleteMapping("{id}")
    public CoursDetails deleteCour(@PathVariable Long id){

        return coursService.deleteCours(id);
    }

    @PutMapping("{id}")
    public Cours updateCours(@PathVariable Long id ,@RequestBody Cours cours)throws Exception{
            return coursService.updateCours(id,cours);
    }
}
