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
	private static final Logger             logger = LoggerFactory.getLogger(GradeController.class);
	
	@GetMapping("/")
	public String view(Model model)
	{
		return "main-balance-set";
	}
	
	@PostMapping("/grade-add-save.html")
	public String gradeAddSave(Model model, @RequestParam("basicSalary") Double basicSalary)
	{
		GradeEntity entity6 = new GradeEntity(6,2,basicSalary);
		basicSalary+=5000;
		GradeEntity entity5 = new GradeEntity(5,2,basicSalary);
		basicSalary+=5000;
		GradeEntity entity4 = new GradeEntity(4,2,basicSalary);
		basicSalary+=5000;
		GradeEntity entity3 = new GradeEntity(3,2,basicSalary);
		basicSalary+=5000;
		GradeEntity entity2 = new GradeEntity(2,1,basicSalary);
		basicSalary+=5000;
		GradeEntity entity1 = new GradeEntity(1,1,basicSalary);
		
		gradeRepo.save(entity1);
		gradeRepo.save(entity2);
		gradeRepo.save(entity3);
		gradeRepo.save(entity4);
		gradeRepo.save(entity5);
		gradeRepo.save(entity6);

		logger.info("Grade Information: ");
		List<GradeEntity> gradeList = gradeRepo.findAll();
		for(GradeEntity grade: gradeList)
			logger.info(""+ grade);
		
		return "redirect:emp-info-add.html";
	}

	
}
