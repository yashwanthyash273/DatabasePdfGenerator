package com.example.pdfgenerator.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.pdfgenerator.model.Employee;


@Repository
public interface EmployeeService {

	public List<Employee> getEmployeeData();
	
	
}
