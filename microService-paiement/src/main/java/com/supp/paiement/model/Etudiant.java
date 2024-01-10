package com.supp.paiement.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Etudiant {

    private Long id;
    private String firstName;
    private String lastName;
    private String Email;
    private String niveau;
    private Long id_Groupe;
}
