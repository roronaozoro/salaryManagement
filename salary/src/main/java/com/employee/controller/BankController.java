package com.employee.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.employee.entity.BankEntity;
import com.employee.entity.GradeEntity;
import com.employee.service.BankService;

@Controller
public class BankController 
{
	private static final Logger             logger = LoggerFactory.getLogger(BankController.class);
			
	@Autowired BankService bankService;
	
	@PostMapping("main-balance-set-save.html")
	public String balanceSettingSave(Model model,@Valid @ModelAttribute BankEntity balance)
	{
		logger.info("Bank balance: "+balance);
		model.addAttribute("grade", new GradeEntity());
		bankService.setBalanceAccount(balance.getBankBalance());
		return "grade-add";
	}
	
	@ResponseBody
	@PostMapping("main-balance-add-save.html")
	public String balanceAddSave(@Valid @ModelAttribute BankEntity balance)
	{
		Double total=0d;
		BankEntity storedEntity = bankService.getBalanceAccount();
		if(storedEntity!=null)
			total = storedEntity.getBankBalance();
		
		total=total+balance.getBankBalance();
		logger.info("Bank balance: "+balance);
		BankEntity entity = bankService.setBalanceAccount(total);
		String str = entity.toString();
		if(entity.getRequiredBalance()==0)
			str += ". Please reload balance tranfer page.";
		
		return str;
	}

}
