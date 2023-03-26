package com.example.pdfgenerator.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pdfgenerator.DBUtil.DBUtil;
import com.example.pdfgenerator.model.Employee;
import com.example.pdfgenerator.service.EmployeeService;

import jakarta.annotation.PreDestroy;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	static List<Employee> employeelist = new ArrayList();
	
	Connection connection;
	
	public EmployeeServiceImpl() throws SQLException {
		connection = DBUtil.getConnection();
	}
	
	@Override
	public List<Employee> getEmployeeData() {
	    if (employeelist.isEmpty()) { // check if list is empty before making database connection
	        try { 
	            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employee_data"); 
	            ResultSet rs = stmt.executeQuery();  
	            while(rs.next()) { 
	                Employee emp = new Employee(); 
	                emp.setId(rs.getInt(1));
	                emp.setName(rs.getString(2)); 
	                emp.setSalary(rs.getString(3));
	                employeelist.add(emp);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        } finally {
	            try {
	                if (connection != null) {
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return employeelist; 
	}

	}


