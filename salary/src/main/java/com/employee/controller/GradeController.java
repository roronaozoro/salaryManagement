package com.employee.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.employee.entity.GradeEntity;
import com.employee.repo.EmployeeRepository;
import com.employee.repo.GradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class GradeController 
{
	@Autowired GradeRepository gradeRepo; 
	@Autowired EmployeeRepository empRepo;
	private static final Logger logger = LoggerFactory.getLogger(GradeController.class);
	private final double addition = 5000d;
	
	
	@PostMapping("/grade-add-save.html")
	public String gradeAddSave(Model model, @RequestParam("basicSalary") Double basicSalary)
	{
		int count=0;
		int employeeNumber=2;
		for(int grade=6;grade>=1;grade--)
		{
			if(grade<=2)
				employeeNumber=1;
			GradeEntity gradeEntity = new GradeEntity(grade,employeeNumber,basicSalary+(addition*count++));
		    gradeRepo.save(gradeEntity);
		}

		logger.info("Grade Information: ");
		List<GradeEntity> gradeList = gradeRepo.findAll();
		for(GradeEntity grade: gradeList)
			logger.info(""+ grade);
		
		return "redirect:emp-info-add.html";
	}

	
}
