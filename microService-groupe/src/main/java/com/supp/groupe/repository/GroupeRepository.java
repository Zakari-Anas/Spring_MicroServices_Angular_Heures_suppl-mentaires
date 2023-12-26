package com.supp.groupe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;

import com.supp.groupe.model.Groupe;

@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Long> {

	
}
