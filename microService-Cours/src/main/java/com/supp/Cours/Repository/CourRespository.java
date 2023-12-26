package com.supp.Cours.Repository;

import com.supp.Cours.model.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourRespository extends JpaRepository<Cours,Long> {
}
