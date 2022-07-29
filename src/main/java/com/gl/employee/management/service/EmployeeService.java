package com.gl.employee.management.service;

import java.util.List;

import com.gl.employee.management.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();

	public Employee findById(Integer id);

	public Employee save(Employee employee);

	public void deleteById(Integer id);
	
	public List<Employee> findByFirstName(String firstName);
	
	public List<Employee> findByFirstNameOrdered(String order);
	
}