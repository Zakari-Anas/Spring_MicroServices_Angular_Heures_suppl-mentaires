package com.supp.groupe.model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Groupe {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="count")
    private String count;
    @Column(name="IdProf")
    private Long id_prof;
//    @OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Etudiant> etudiants = new HashSet<>();
}
