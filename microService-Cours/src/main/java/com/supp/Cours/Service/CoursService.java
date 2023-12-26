package com.supp.Cours.Service;

import com.supp.Cours.Repository.CourRespository;
import com.supp.Cours.model.Cours;
import com.supp.Cours.model.CoursDetails;
import com.supp.Cours.model.Prof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class CoursService {

    @Autowired
    private CourRespository courRespository;
    @Autowired
    private RestTemplate restTemplate;
    private final String URLPROFS="http://localhost:8888/SERVICE-PROF";
    public List<CoursDetails> findAll(){

        List<Cours> cours=courRespository.findAll();
        ResponseEntity<Prof[]> response=restTemplate.getForEntity(this.URLPROFS+"/api/Employees/",Prof[].class);
        Prof[]profs=response.getBody();

        return cours.stream().map((Cours cour)->
                mapToCoursResponse(cour,profs))
                .toList();


    }


    private CoursDetails mapToCoursResponse(Cours cour,Prof[] profs) {

        Prof prof= Arrays.stream(profs)
                .filter(proff->proff.getId().equals(cour.getId_prof()))
                .findFirst()
                .orElse(null);


        return CoursDetails.builder()
                .nom(cour.getNom())
                .Duree(cour.getDuree())
                .prof(prof)
                .build();

    }
    public CoursDetails getCoursById( Long id) throws Exception{

            Cours courById=courRespository.findById(id).orElseThrow(()->new Exception("Cour doesn't existe"));
            Prof profBYCour=restTemplate.getForObject(this.URLPROFS+"/api/Employees/"+courById.getId_prof(),Prof.class);
            return  CoursDetails.builder()
                    .Duree(courById.getDuree())
                    .nom(courById.getNom())
                    .prof(profBYCour)
                    .build();
        }

        public Cours addCours(Cours cour){

        return courRespository.save(cour);
    }

}
