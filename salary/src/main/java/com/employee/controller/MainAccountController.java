package com.employee.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.employee.entity.AccountEntity;
import com.employee.entity.BankEntity;
import com.employee.entity.EmployeeEntity;
import com.employee.entity.GradeEntity;
import com.employee.repo.EmployeeRepository;
import com.employee.repo.GradeRepository;
import com.employee.service.BankService;

@Controller
public class MainAccountController 
{
	@Autowired BankService bankService;
    @Autowired EmployeeRepository empRepo;
    @Autowired GradeRepository gradeRepo;
	
	@GetMapping("balance-transfer")
	public String accountOperation(Model model)
	{
		Double transfer = bankService.transferAmount();
		Double balance  = bankService.getBalanceAccount().getBankBalance();
		if(transfer>balance)
		{
			BankEntity bank = bankService.getBalanceAccount();
			bank.setRequiredBalance(transfer-balance);
			model.addAttribute("balance", bank);
			return "main-balance-add";
		}
		else
		{
			model.addAttribute("bankBalance", bankService.transferAmount());
			return "balance-transfer";
		}
	}
	
	@ResponseBody
	@PostMapping("transfer-balance-add-save.html")
	public String transferBalance(Model model,@RequestParam Double bankBalance)
	{
		BankEntity store = bankService.getBalanceAccount();
		Double amount  = store.getBankBalance()-bankBalance;
		bankService.setBalanceAccount(amount);
		
		List<EmployeeEntity> list  = empRepo.findAll();
		
		for(EmployeeEntity emp: list)
		{
			AccountEntity acc = emp.getAccount();
			Double storeAcc = acc.getCurrentBalance();
			GradeEntity grade = gradeRepo.getOne(emp.getGrade());
			Double calculateAmount = grade.getBasicSalary()+grade.getHouseRent()+grade.getMedicalAllowance();
			acc.setCurrentBalance(storeAcc+calculateAmount);
			empRepo.save(emp);
		}
		return "Total paid salary: "+bankBalance+". Remaining balance: "+amount;
	}

}
