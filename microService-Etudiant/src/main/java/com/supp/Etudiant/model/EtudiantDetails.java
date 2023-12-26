package com.supp.Etudiant.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class EtudiantDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private String Email;
    private String niveau;
    private Groupe groupe;
}
