package com.supp.Etudiant.Service;

import com.supp.Etudiant.Repository.EtudiantRepository;
import com.supp.Etudiant.model.Etudiant;
import com.supp.Etudiant.model.EtudiantDetails;
import com.supp.Etudiant.model.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class EtudiantService {

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String URLGroups="http://localhost:8888/SERVICE-GROUPE";

    public List<EtudiantDetails> findAll(){

        List<Etudiant> etudiants=etudiantRepository.findAll();
        ResponseEntity<Groupe[]> response=restTemplate.getForEntity(this.URLGroups+"/api/Groupe/",Groupe[].class);
        Groupe[] groupe=response.getBody();

        return etudiants.stream().map((Etudiant e)->
                        mapToEtudiantResponse(e,groupe))
                .toList();


    }
    public EtudiantDetails findEtudiantByID(Long id) throws Exception{

        Etudiant etudiant= etudiantRepository.findById(id).orElseThrow(()->new Exception("Group Invalid"));
        Groupe groupe =restTemplate.getForObject(this.URLGroups+"/api/Groupe/"+etudiant.getId_Groupe(),Groupe.class);


        return EtudiantDetails.builder()
                .id(etudiant.getId())
                .firstName(etudiant.getFirstName())
                .lastName(etudiant.getLastName())
                .Email(etudiant.getEmail())
                .niveau(etudiant.getNiveau())
                .groupe(groupe)
                .build();

    }

    public Etudiant UpdateEtudiant(Etudiant e, Long id) throws Exception {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(() -> new Exception("Group Invalid"));

        etudiant.setEmail(e.getEmail());
        etudiant.setFirstName(e.getFirstName());
        etudiant.setLastName(e.getLastName());
        etudiant.setNiveau(e.getNiveau());
        etudiant.setId_Groupe(e.getId_Groupe());
        etudiantRepository.save(etudiant);
        return etudiant;
    }

    public List<Groupe> allGroups(){

        Groupe[] groupes = restTemplate.getForObject(this.URLGroups+"/api/Groupe/", Groupe[].class);
        List<Groupe> groupList = Arrays.asList(groupes);


        groupList.forEach(group -> {
            System.out.println("Group ID: " + group.getId() + ", Group Name: " + group.getName());
        });

        return groupList;
    }

  //  public List<EtudiantDetails> getEtudiantByName(String firstname){
    //    String query = "SELECT * FROM Etudiant_management_system WHERE firstName = :firstname";

        //return etudiantRepository.findByFirstName(firstname);
    //}
    public List<EtudiantDetails> getEtudiantByGroupe(Long id){

        List<Etudiant> etudiants=etudiantRepository.findAll();
        ResponseEntity<Groupe[]> response=restTemplate.getForEntity(this.URLGroups+"/api/Groupe/",Groupe[].class);
        Groupe[] groupes=response.getBody();

        List<Etudiant> etudiantsInGroup = etudiants.stream()
                .filter(e -> e.getId_Groupe().equals(id))
                .toList();

        // Map filtered etudiants to EtudiantDetails
        return  etudiantsInGroup.stream()
                .map(etudiant -> mapToEtudiantResponse(etudiant, groupes))
                .toList();
    }

    public List<Etudiant> getAllEtudiant(){

        return  etudiantRepository.findAll();
    }
    private EtudiantDetails mapToEtudiantResponse(Etudiant etudiant,Groupe[] groupes){


        Groupe foundGroup= Arrays.stream(groupes)
                .filter(groupe->groupe.getId().equals(etudiant.getId_Groupe())).findFirst()
                .orElse(null);

        return EtudiantDetails.builder()
                .id(etudiant.getId())
                .firstName(etudiant.getFirstName())
                .lastName(etudiant.getLastName())
                .Email(etudiant.getEmail())
                .niveau(etudiant.getNiveau())
                .groupe(foundGroup)
                .build();

    }


}
