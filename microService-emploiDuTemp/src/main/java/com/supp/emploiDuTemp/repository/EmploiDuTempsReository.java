package com.supp.emploiDuTemp.repository;

import com.supp.emploiDuTemp.model.EmploiDuTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploiDuTempsReository extends JpaRepository<EmploiDuTemp,Long> {
}
