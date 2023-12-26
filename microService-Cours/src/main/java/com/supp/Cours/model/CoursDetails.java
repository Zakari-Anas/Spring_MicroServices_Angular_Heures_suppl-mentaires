package com.supp.Cours.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class CoursDetails {
    private Long id;

    private String nom;

    private float Duree;

    private Prof prof;
}
