package com.supp.reservation.controller;

import com.supp.reservation.model.Reservation;
import com.supp.reservation.model.ReservationDetails;
import com.supp.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation/")
@CrossOrigin(origins = "http://localhost:4200" ,allowedHeaders = "*")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @GetMapping()
    private List<ReservationDetails> getAll(){
        return reservationService.findAll();
    }
    @PostMapping("New")
    private Reservation AddReservation(@RequestBody Reservation reservation){
        return reservationService.AjouterReservation(reservation);
    }

    @PutMapping("{id}")
    private Reservation UpdateReservation(@PathVariable Long id , @RequestBody Reservation reservation) throws Exception{
        return reservationService.update(id,reservation);
    }

    @DeleteMapping("{id}")
    private void Delete(@PathVariable Long id) throws Exception{
        reservationService.DelteReservation(id);
    }

}
