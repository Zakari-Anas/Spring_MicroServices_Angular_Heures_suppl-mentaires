package com.supp.paiement.service;

import com.supp.paiement.model.Etudiant;
import com.supp.paiement.model.Groupe;
import com.supp.paiement.model.PaiemantDetails;
import com.supp.paiement.model.Paiement;
import com.supp.paiement.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PaiementService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaiementRepository paiementRepository;

    private final String URLGroups="http://localhost:8888/SERVICE-GROUPE";
    private final String UrlEtudiants = "http://localhost:8888/SERVICE-ETUDIANT";

    public Paiement AjouterPaiment(@RequestBody Paiement paiement) {


        // Proceed with the operations
        paiementRepository.save(paiement);
        // Etudiant etudiant = restTemplate.getForObject(this.UrlEtudiants + "/api/Etudiants/" + id, Etudiant.class);
        //Groupe groupe = restTemplate.getForObject(this.URLGroups + "/api/Groupe/" + etudiant.getId_groupe(), Groupe.class);
        return paiement;

    }


    public List <PaiemantDetails> afficherLesPaiements() {
        List<Paiement> paiements = paiementRepository.findAll();

        ResponseEntity<Etudiant[]> etudiantList = restTemplate.getForEntity(UrlEtudiants + "/api/Etudiants/all", Etudiant[].class);
        Etudiant[] etudiants = etudiantList.getBody();

        ResponseEntity<Groupe[]> response=restTemplate.getForEntity(this.URLGroups+"/api/Groupe/",Groupe[].class);
        Groupe[] groupe=response.getBody();

        return  paiements.stream().map((Paiement paiement) -> mapToPaiementResponse(paiement, etudiants, groupe)).toList();
    }


    private PaiemantDetails mapToPaiementResponse(Paiement paiement,Etudiant[] etudiants,Groupe[] groupes) {

        Etudiant etudiant= Arrays.stream(etudiants)
                .filter(etudiants1 -> etudiants1.getId().equals(paiement.getId_etudiant()))
                .findFirst()
                .orElse(null);



         Groupe groupe = Arrays.stream(groupes)
                .filter(groupe1 -> groupe1.getId().equals(etudiant != null ? etudiant.getId_Groupe() : new Exception("groupe not found")))
                .findFirst()
                .orElse(null);

        return PaiemantDetails.builder()
                .id(paiement.getId())
                .etudiant(etudiant)
                .groupe(groupe)
                .paymentDate(paiement.getPaymentDate())
                .build();
    }

    public void deletePaiment(Long id){
        paiementRepository.deleteById(id);
    }

    public Paiement Update(Paiement paiement,Long id ) throws Exception{

        Paiement paiement1=paiementRepository.findById(id).orElseThrow(()->new Exception("Paiement not found "));

        paiement1.setPaymentDate(paiement.getPaymentDate());
        paiement1.setId_etudiant(paiement.getId_etudiant());

       return paiementRepository.save(paiement1);
    }

    public Paiement getPaiement(Long id ) throws Exception{
        Paiement paiement1=paiementRepository.findById(id).orElseThrow(()->new Exception("Paiement not found "));

        return paiement1;

    }

}
