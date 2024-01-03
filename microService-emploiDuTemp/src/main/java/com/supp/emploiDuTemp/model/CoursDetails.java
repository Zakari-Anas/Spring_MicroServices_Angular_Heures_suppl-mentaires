package com.supp.emploiDuTemp.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CoursDetails {
    private Long id;

    private String nom;

    private Float duree;

    private Prof prof;
}
