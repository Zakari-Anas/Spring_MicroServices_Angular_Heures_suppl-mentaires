package com.supp.Absence.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Etudiant {
    private Long id;

    private String firstName;

    private String lastName;

    private String Email;

    private String niveau;
    private Long id_Groupe;
}
