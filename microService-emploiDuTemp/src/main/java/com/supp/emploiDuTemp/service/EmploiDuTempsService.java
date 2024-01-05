package com.supp.emploiDuTemp.service;

import com.supp.emploiDuTemp.model.CoursDetails;
import com.supp.emploiDuTemp.model.EmploiDuTemp;
import com.supp.emploiDuTemp.model.EmploiDuTempsDetails;
import com.supp.emploiDuTemp.model.Groupe;
import com.supp.emploiDuTemp.repository.EmploiDuTempsReository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class EmploiDuTempsService {
    private final String URLGroups="http://localhost:8888/SERVICE-GROUPE";
    private final String URLCours="http://localhost:8888/SERVICE-COURS";

    @Autowired
    private EmploiDuTempsReository emploiDuTempsReository;
    @Autowired
    private RestTemplate restTemplate;
    public EmploiDuTemp createNewSupp(EmploiDuTemp emploiDuTemp){

       return emploiDuTempsReository.save(emploiDuTemp);
    }

    public EmploiDuTempsDetails findEMploiById(Long id) throws Exception{
       EmploiDuTemp emploiDuTemp= emploiDuTempsReository.findById(id).orElseThrow(()->new Exception("Enregistrement introuvable"));

        Groupe groupe=restTemplate.getForObject(this.URLGroups+"/api/Groupe/"+emploiDuTemp.getGroupe_id(),Groupe.class);

        CoursDetails coursDetails=restTemplate.getForObject(this.URLCours+"/api/Cours/"+emploiDuTemp.getCours_id(),CoursDetails.class);

       return EmploiDuTempsDetails.builder()
                .groupe(groupe)
                .coursDetails(coursDetails)
                .heure_debut(emploiDuTemp.getHeure_debut())
                .heure_fin(emploiDuTemp.getHeure_fin())
                .build();

    }
    public void deleteEmploi(Long id){

        emploiDuTempsReository.deleteById(id);
    }

    public List<EmploiDuTempsDetails> getAll() {

        List<EmploiDuTemp> emploiDuTempList = emploiDuTempsReository.findAll();
        ResponseEntity<Groupe[]> response=restTemplate.getForEntity(this.URLGroups+"/api/Groupe/",Groupe[].class);
        Groupe[] groups=response.getBody();
        ResponseEntity<CoursDetails[]> response1=restTemplate.getForEntity(this.URLCours+ "/api/Cours/",CoursDetails[].class);
        CoursDetails[] coursDetails=response1.getBody();

        return emploiDuTempList.stream().map((EmploiDuTemp emploiDuTemp)->
                        mapToEmploiResponse(emploiDuTemp,groups,coursDetails))
                        .toList();


    }


    public EmploiDuTempsDetails UpdateEmploi(EmploiDuTemp emploiDuTemp,Long id) throws Exception{

        EmploiDuTemp emploiDuTemp1=emploiDuTempsReository.findById(id).orElseThrow(()->new Exception("Enregistrement introuvable"));
        emploiDuTemp1.setCours_id(emploiDuTemp.getCours_id());
        emploiDuTemp1.setGroupe_id(emploiDuTemp.getGroupe_id());
        emploiDuTemp1.setHeure_debut(emploiDuTemp.getHeure_debut());
        emploiDuTemp1.setHeure_fin(emploiDuTemp.getHeure_fin());

        Groupe groupe=restTemplate.getForObject(this.URLGroups+"/api/Groupe/"+emploiDuTemp1.getGroupe_id(),Groupe.class);

        CoursDetails Cours=restTemplate.getForObject(this.URLCours+ "/api/Cours/"+emploiDuTemp1.getCours_id(),CoursDetails.class);

        emploiDuTempsReository.save(emploiDuTemp1);
        return EmploiDuTempsDetails.builder()
                .groupe(groupe)
                .coursDetails(Cours)
                .heure_debut(emploiDuTemp1.getHeure_debut())
                .heure_fin(emploiDuTemp1.getHeure_fin())
                .build();


    }


    private EmploiDuTempsDetails mapToEmploiResponse(EmploiDuTemp emploiDuTemp,Groupe []groupe,CoursDetails [] coursDetails){

        Groupe grp= Arrays.stream(groupe)
                .filter(groupe1 -> groupe1.getId().equals(emploiDuTemp.getGroupe_id()))
                .findFirst()
                .orElse(null);

        CoursDetails crsD=Arrays.stream(coursDetails)
                .filter(coursDetails1 -> coursDetails1.getId().equals(emploiDuTemp.getCours_id()))
                .findFirst()
                .orElse(null);

        return EmploiDuTempsDetails.builder()
                .id(emploiDuTemp.getId())
                .heure_debut(emploiDuTemp.getHeure_debut())
                .heure_fin(emploiDuTemp.getHeure_fin())
                .groupe(grp)
                .coursDetails(crsD)
                .build();

    }
}
