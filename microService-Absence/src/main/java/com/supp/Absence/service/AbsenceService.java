package com.supp.Absence.service;

import com.supp.Absence.model.*;
import com.supp.Absence.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AbsenceService {
    private final String URLGroups="http://localhost:8888/SERVICE-GROUPE";
    private final String URLEtudiants="http://localhost:8888/SERVICE-ETUDIANT";
    private final String URLCours="http://localhost:8888/SERVICE-COURS";
    @Autowired
    private AbsenceRepository absenceRepository;
    @Autowired
    private RestTemplate restTemplate;
    public Absence createAbsence(Absence absence){

        return absenceRepository.save(absence);
    }

    public Absence UpdateAbsence(Absence absence ,Long id ) throws Exception{
        Absence absence1= absenceRepository.findById(id).orElseThrow(()->new Exception("absence not found"));
        absence1.setHoraire(absence.getHoraire());
        absence1.setId_cours(absence.getId_cours());
        absence1.setId_etudiant(absence.getId_etudiant());
        absence1.setId_groupe(absence.getId_groupe());
        absence1.setJustification(absence.getJustification());
        return absenceRepository.save(absence1);
    }
    public void deleteAbsence(Long id){

        absenceRepository.deleteById(id);
    }

    public Absence findById(Long id)throws  Exception{
        return absenceRepository.findById(id).orElseThrow(()->new Exception("null Absence"));
    }

    public List<AbsenceDetails> allAbsence(){

        List<Absence>absencesList=absenceRepository.findAll();
        ResponseEntity<Groupe[]> groupeList=restTemplate.getForEntity(this.URLGroups+"/api/Groupe/",Groupe[].class);
        Groupe[] groupe = groupeList.getBody();
        ResponseEntity<Etudiant[]> etudiantList=restTemplate.getForEntity(this.URLEtudiants+"/api/Etudiants/all",Etudiant[].class);
        Etudiant[] etudiants=etudiantList.getBody();
        ResponseEntity<Cours[]> courList=restTemplate.getForEntity(this.URLCours+"/api/Cours/",Cours[].class);
        Cours[] cours=courList.getBody();
            return absencesList.stream()
                    .map((Absence absence)->mapToAbsenceResponse(absence, groupe,cours,etudiants))
                    .toList();
    }


    private  AbsenceDetails mapToAbsenceResponse(Absence absence,Groupe[] groupes,Cours[] cours,Etudiant[] etudiants){


        Etudiant etudiant=Arrays.stream(etudiants)
                .filter(etudiant1 -> etudiant1.getId().equals(absence.getId_etudiant()))
                .findFirst()
                .orElse(null);

        Groupe groupe1= Arrays.stream(groupes)
                        .filter(groupe -> groupe.getId().equals(etudiant.getId_Groupe()))
                        .findFirst()
                        .orElse(null);
        Cours cours1=Arrays.stream(cours)
                        .filter(cours2 -> cours2.getId().equals(absence.getId_cours()))
                        .findFirst()
                        .orElse(null);

        return AbsenceDetails.builder()
                .id(absence.getId())
                .horaire(absence.getHoraire())
                .etudiant(etudiant)
                .groupe(groupe1)
                .cours(cours1)
                .justification(absence.getJustification())
                .build();

    }



}
