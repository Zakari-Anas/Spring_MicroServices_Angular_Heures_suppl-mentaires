package com.supp.employe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.supp.employe.exception.ResourceNotFoundException;
import com.supp.employe.model.Employee;
import com.supp.employe.repository.EmployeeRepository;

		@RestController
		@RequestMapping("/api/Employees/")
		@CrossOrigin(origins = "http://localhost:4200" ,allowedHeaders = "*")

public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	//getAllEmployees 
	@GetMapping()
	public List<Employee> getAllEmployees(){
			return employeeRepository.findAll();
	}
	
	//find by id 
	@GetMapping("/{id}")
	public ResponseEntity<Employee> findEmployee(@PathVariable Long id) {
	    Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employe doesn't existe!!"));   
	   
	        return ResponseEntity.ok(employee);
	}
	//add new Prof 
	@PostMapping("/New")
	public Employee createEmploye(@RequestBody Employee e) {
		return employeeRepository.save(e);
	}
	
	//delete Prof
	@DeleteMapping("/{id}")
	public void DeleteEmployeeById(@PathVariable Long id ) {
		
		 employeeRepository.deleteById(id);
	}
	//Update Prof
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
	    Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employe doesn't existe!!"));   
	    employee.setEmail(employeeDetails.getEmail());
	    employee.setFirstName(employeeDetails.getFirstName());
	    employee.setLastName(employeeDetails.getLastName());
	    
	    Employee updatedEmployee= employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
		
	}
		
}
