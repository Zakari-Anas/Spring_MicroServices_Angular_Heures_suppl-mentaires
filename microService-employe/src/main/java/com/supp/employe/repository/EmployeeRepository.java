package com.supp.employe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supp.employe.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long >{

}
