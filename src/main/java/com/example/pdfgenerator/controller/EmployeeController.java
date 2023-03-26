package com.example.pdfgenerator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pdfgenerator.model.Employee;
import com.example.pdfgenerator.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService empservice;
	
	@GetMapping("/employees")
	public List<Employee> getEmployees()
	{
		return this.empservice.getEmployeeData();
	}
	
	
}
