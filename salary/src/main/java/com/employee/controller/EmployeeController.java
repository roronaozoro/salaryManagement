package com.employee.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.employee.entity.AccountEntity;
import com.employee.entity.EmployeeEntity;
import com.employee.repo.AccountRepository;
import com.employee.repo.EmployeeRepository;
import com.employee.repo.GradeRepository;
import com.employee.service.SalarySheetService;
import com.itextpdf.text.DocumentException;

@Controller
public class EmployeeController 
{
	@Autowired EmployeeRepository empRepo;
	@Autowired GradeRepository gradeRepo;
	@Autowired AccountRepository accountRepo;
	@Autowired SalarySheetService salaryService;
	
	private static final Logger             logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@GetMapping("emp-info-add.html")
	public String empInfo(Model model)
	{
		model.addAttribute("gradeList", gradeRepo.findAll());
		model.addAttribute("employee", new EmployeeEntity());
		model.addAttribute("employeeList", empRepo.findAll());
		return "emp-info-add";
	}
	
	@ResponseBody
	@PostMapping("employee-add-save.html")
	public String empSave(@Valid @ModelAttribute EmployeeEntity employee,BindingResult result,@RequestParam Integer grade)
	{
		if(result.hasErrors())
		{
			logger.info("Error: "+result.getAllErrors());
		}
		AccountEntity acc = accountRepo.save(employee.getAccount());
		employee.setAccount(acc);
		EmployeeEntity emp  = empRepo.save(employee);
		logger.info("EMP INFO: "+emp);
		return "Employee Info :"+emp+" Saved Successfully";
	}
	
	@ResponseBody
	@GetMapping("employee-salary-sheet.pdf")
	public void salarySheet(HttpServletResponse response) throws IOException, DocumentException
	{
		response.setContentType("application/pdf");
       
		salaryService.createSalarySheet(response.getOutputStream());
        
	}

}
