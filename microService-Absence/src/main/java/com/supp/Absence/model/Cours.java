package com.supp.Absence.model;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Cours {

    private Long id;
    private String nom;
    private Float duree;
    private Long id_prof;
}
