package com.employee.service.impl;

import javax.servlet.ServletOutputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.employee.entity.EmployeeEntity;
import com.employee.entity.GradeEntity;
import com.employee.repo.EmployeeRepository;
import com.employee.repo.GradeRepository;
import com.employee.service.SalarySheetService;

@Service
public class SalarySheetServiceImpl implements SalarySheetService{
	
	@Autowired EmployeeRepository repository;
    @Autowired GradeRepository gradeRepo;
	@Override
	public void createSalarySheet(ServletOutputStream outputStream) throws DocumentException {
		 
		Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        
        Paragraph p = new Paragraph("Salary Sheet", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {0.5f, 1.5f, 1.5f, 1.5f, 1.5f, 2.5f});
        table.setSpacingBefore(30);
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,10f);
        String[] list = {"SL.","Emp Name","Rank","Basic","House Rent","Medical Allowance"};
        
        PdfPCell tableCell;
        for(String str: list)
        {
            tableCell = cell(headFont,str);
            table.addCell(tableCell);
        }
        font = FontFactory.getFont(FontFactory.HELVETICA,9f);
        List<EmployeeEntity> empList = repository.findAll();
        Integer count=1;
        for(EmployeeEntity emp: empList)
        {
        	GradeEntity grade = gradeRepo.findById(emp.getGrade()).get();
            table.addCell(data(font,count.toString()));
            count++;
            table.addCell(data(font,emp.getEmpName()));
            table.addCell(data(font,emp.getGrade().toString()));
            table.addCell(data(font,String.format("%.2f", grade.getBasicSalary())));
            table.addCell(data(font,String.format("%.2f", grade.getHouseRent())));
            table.addCell(data(font,String.format("%.2f", grade.getMedicalAllowance())));
        }
        
        document.add(table);
         
        document.close();
		
		
	}

	private PdfPCell cell(Font headFont,String str) {
		PdfPCell tableCell;
		tableCell = new PdfPCell(new Phrase(str, headFont));
        tableCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return tableCell;
	}

	
	private PdfPCell data(Font headFont,String str) {
		PdfPCell tableCell;
		tableCell = new PdfPCell(new Phrase(str, headFont));
        tableCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tableCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return tableCell;
	}
	

}
