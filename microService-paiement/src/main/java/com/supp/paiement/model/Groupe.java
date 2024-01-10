package com.supp.paiement.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Groupe {

    private Long id;
    private String name;
    private String count;

}