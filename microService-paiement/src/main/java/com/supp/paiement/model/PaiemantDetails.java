package com.supp.paiement.model;


import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PaiemantDetails {


    private Long id;

    private LocalDate paymentDate;

    private Etudiant etudiant;

    private Groupe groupe;
}
