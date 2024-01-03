package com.supp.Etudiant.Repository;

import com.supp.Etudiant.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    List<Etudiant> findByFirstName(String firstName);
}
