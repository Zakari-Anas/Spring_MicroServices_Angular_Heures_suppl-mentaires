package com.supp.emploiDuTemp.controller;

import com.supp.emploiDuTemp.model.EmploiDuTemp;
import com.supp.emploiDuTemp.model.EmploiDuTempsDetails;
import com.supp.emploiDuTemp.service.EmploiDuTempsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Extra/")
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



}
