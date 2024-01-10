package com.supp.emploiDuTemp.controller;

import com.supp.emploiDuTemp.model.EmploiDuTemp;
import com.supp.emploiDuTemp.model.EmploiDuTempsDetails;
import com.supp.emploiDuTemp.service.EmploiDuTempsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Extra/")
@CrossOrigin(origins = "http://localhost:4200" ,allowedHeaders = "*")
public class EmploiDuTempController {

    @Autowired
    private EmploiDuTempsService emploiDuTempsService;


    @GetMapping
    private List<EmploiDuTempsDetails> getAll(){

        return emploiDuTempsService.getAll();
    }
    @PostMapping("/New")
    private EmploiDuTemp createNew(@RequestBody EmploiDuTemp emploiDuTemp){

       return emploiDuTempsService.createNewSupp(emploiDuTemp);

    }

    @DeleteMapping("{id}")

    private void deleteEmploi(@PathVariable Long id){
        emploiDuTempsService.deleteEmploi(id);
    }

    @PutMapping("/update/{id}")
    private EmploiDuTempsDetails UpdateEmploi(@RequestBody EmploiDuTemp emploiDuTemp,@PathVariable Long id) throws Exception{
        return emploiDuTempsService.UpdateEmploi(emploiDuTemp,id);
    }

    @GetMapping("{id}")
    private EmploiDuTempsDetails findEMploiById(@PathVariable Long id) throws Exception{
        return emploiDuTempsService.findEMploiById(id);
    }



}
