package com.supp.emploiDuTemp.model;

import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EmploiDuTempsDetails {

    private Long id;

    private LocalTime heure_debut;

    private LocalTime heure_fin;

    private Groupe groupe;

    private CoursDetails coursDetails;
}
