package com.supp.Absence.controller;

import com.supp.Absence.model.Absence;
import com.supp.Absence.model.AbsenceDetails;
import com.supp.Absence.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Absence/")
@CrossOrigin(origins = "http://localhost:4200" ,allowedHeaders = "*")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;

    @GetMapping
    private List<AbsenceDetails> getAll(){
        return absenceService.allAbsence();
    }

    @PostMapping("/Add")
    private Absence add(@RequestBody Absence absence){
        return absenceService.createAbsence(absence);
    }

    @DeleteMapping("{id}")
    private void deleteAbsence(@PathVariable Long id){
        absenceService.deleteAbsence(id);
    }

    @PutMapping("{id}")
    private Absence UpdateAbsence(@RequestBody Absence absence,@PathVariable Long id) throws Exception {

        return absenceService.UpdateAbsence(absence,id);
    }

    @GetMapping("{id}")
    private Absence findById(@PathVariable Long id)throws Exception{
        return absenceService.findById(id);
    }
}
