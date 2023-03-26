package com.example.pdfgenerator.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pdfgenerator.model.Employee;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class PDFGeneratrorService {
	@Autowired
	private EmployeeService empservice;
    public void export(HttpServletResponse response) throws IOException {
        //create the document
   
    	List<Employee> result=this.empservice.getEmployeeData();
    	
    	Document document = new Document(PageSize.A4);
    	//getoutputstream throws exception so IOException is added
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Generated Data", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph2 = new Paragraph("All the Employee", fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph);
        document.add(paragraph2);
        for (Employee employee : result) {
            Paragraph paragraph3 = new Paragraph("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Salary: " + employee.getSalary());
            document.add(paragraph3);
        }
  
        document.close();
        System.out.println("PDF created successfully!");
    }
}