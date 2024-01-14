package com.supp.Absence.model;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AbsenceDetails {
    private Long id;
    private LocalDate horaire;
    private String justification;
    private Groupe groupe;
    private Cours cours;
    private Etudiant etudiant;
}
