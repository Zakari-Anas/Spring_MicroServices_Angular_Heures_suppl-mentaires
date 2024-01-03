package com.supp.Cours.model;
import com.supp.Cours.model.Prof;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "duree")
    private Float duree;
    @Column(name = "id_prof")
    private Long id_prof;



}
