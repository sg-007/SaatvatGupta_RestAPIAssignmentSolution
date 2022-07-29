package com.gl.employee.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.employee.management.entity.Employee;
import com.gl.employee.management.service.EmployeeService;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("list-employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	@GetMapping("list-employees/sort")
	public List<Employee> findAllOrdered(@RequestParam(value = "order", required = false) String order) {
		return employeeService.findByFirstNameOrdered(order);
	}
	
	@GetMapping()
	public Employee findById(@RequestParam Integer id) {
		Employee employee = employeeService.findById(id);
		if (employee == null) 
			throw new RuntimeException("Employee id not found " + id);
		return employee;
	}
	
	@PostMapping("add-employee")
	public Employee addEmployee(@RequestBody Employee employee) {
		employeeService.save(employee);
		return employee;
	}
	
	@PutMapping("update-employee")
	public Employee updateEmployee(@RequestBody Employee employee, @RequestParam Integer id) {
		Employee tEmployee = employeeService.findById(id);
		
		if (tEmployee == null) {
			throw new RuntimeException("Employee id not found - " + id);
		}
		tEmployee.setFirstName(employee.getFirstName());
		tEmployee.setLastName(employee.getLastName());
		tEmployee.setEmail(employee.getEmail());
		employeeService.save(tEmployee);
		return tEmployee;
	}
	
	@DeleteMapping("delete-employee")
	public String deleteEmployee(@RequestParam Integer id) {
		Employee employee = employeeService.findById(id);
		if (employee == null) 
			throw new RuntimeException("Employee id not found - " + id);
		employeeService.deleteById(id);
		return "Deleted employee with id " + id;
	}
	
	@GetMapping("search")
	public ResponseEntity<List<Employee>> searchEmployee(@RequestParam String firstName) {
		return new ResponseEntity<List<Employee>>(employeeService.findByFirstName(firstName), HttpStatus.OK);
	}
}