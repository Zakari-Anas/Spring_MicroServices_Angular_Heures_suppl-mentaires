package com.supp.paiement.controller;

import com.supp.paiement.model.PaiemantDetails;
import com.supp.paiement.model.Paiement;
import com.supp.paiement.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/Paiement/")
@CrossOrigin(origins = "http://localhost:4200" ,allowedHeaders = "*")
public class PaiementController {

    @Autowired
    PaiementService paiementService;
    @PostMapping("/New")
    private Paiement AjouterPaiment(@RequestBody Paiement paiement){
        return paiementService.AjouterPaiment(paiement);
    }

    @GetMapping()
    private List<PaiemantDetails> getAll(){

        return paiementService.afficherLesPaiements();
    }

    @DeleteMapping( "{id}")
    private void deletePaiment(@PathVariable Long id){
        paiementService.deletePaiment(id);
    }

    @PutMapping("{id}")
    private Paiement update(@RequestBody Paiement paiement,@PathVariable Long id)throws Exception{
        return paiementService.Update(paiement,id);
    }

    @GetMapping("{id}")
    private Paiement getPaiement(@PathVariable Long id) throws Exception{
        return paiementService.getPaiement(id);
    }
}
