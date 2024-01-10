package com.supp.reservation.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Salle {
    private Long id;
    private String nom;
    private double longueur;
    private double largeur;
    private double hauteur;
}
