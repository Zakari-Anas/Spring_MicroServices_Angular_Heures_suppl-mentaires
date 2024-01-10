package com.supp.reservation.service;

import com.supp.reservation.model.Prof;
import com.supp.reservation.model.Reservation;
import com.supp.reservation.model.ReservationDetails;
import com.supp.reservation.model.Salle;
import com.supp.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RestTemplate restTemplate;

    private String URLProfs="http://localhost:8888/SERVICE-PROF";
    private String URLSalles="http://localhost:8888/SERVICE-SALLE";
    public Reservation AjouterReservation(Reservation reservation){

        return reservationRepository.save(reservation);
    }

    public void DelteReservation(Long id) throws Exception{
      Reservation reservation=  reservationRepository.findById(id).orElseThrow(()->new Exception("Reservation doesn't existe"));
        reservationRepository.delete(reservation);
    }

    public List<ReservationDetails> findAll(){

          List<Reservation> reservations=reservationRepository.findAll();
          ResponseEntity<Prof[]> profList=  restTemplate.getForEntity(this.URLProfs+"/api/Employees/",Prof[].class);
          Prof[] profs=profList.getBody();
          ResponseEntity<Salle[]> salleList = restTemplate.getForEntity(this.URLSalles+"/api/Salle/",Salle[].class);
          Salle[] salles=salleList.getBody();

          return reservations.stream().map((Reservation res)->
                  mapToReservationResponse(res,profs,salles)).toList();
    }

    public Reservation update(Long id,Reservation reservation) throws Exception{
        Reservation reservation1=reservationRepository.findById(id).orElseThrow(()->new Exception("Reservation doesn't exist"));

        reservation1.setHoraire(reservation.getHoraire());
        reservation1.setHeure_fin(reservation.getHeure_fin());
        reservation1.setHeure_debut(reservation.getHeure_debut());
        reservation1.setId_Prof(reservation.getId_Prof());
        reservation1.setId_Salle(reservation.getId_Salle());

        return  reservationRepository.save(reservation1);
    }
    public ReservationDetails mapToReservationResponse(Reservation reservation,Prof[] profs,Salle[] salles){

        Prof prof= Arrays.stream(profs)
                .filter((Prof p)->p.getId().equals(reservation.getId_Prof()))
                .findFirst()
                .orElse(null);

        Salle salle =Arrays.stream(salles)
                .filter((Salle s)->s.getId().equals(reservation.getId_Salle()))
                .findFirst()
                .orElse(null);

        return ReservationDetails.builder()
                .Id(reservation.getId())
                .horaire(reservation.getHoraire())
                .heure_debut(reservation.getHeure_debut())
                .heure_fin(reservation.getHeure_fin())
                .prof(prof)
                .salle(salle)
                .build();
    }


}
