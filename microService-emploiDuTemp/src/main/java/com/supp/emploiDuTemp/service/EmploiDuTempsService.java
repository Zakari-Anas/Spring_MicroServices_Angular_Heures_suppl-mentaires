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
