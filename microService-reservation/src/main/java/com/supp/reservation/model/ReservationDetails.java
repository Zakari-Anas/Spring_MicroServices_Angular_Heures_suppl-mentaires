package com.supp.reservation.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class ReservationDetails {

    private Long Id;
    private LocalDate horaire;
    private LocalTime heure_debut;
    private LocalTime heure_fin;
    private Salle salle;
    private Prof prof;
}
