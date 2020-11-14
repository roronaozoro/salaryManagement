package com.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.BankEntity;
import com.employee.entity.GradeEntity;
import com.employee.repo.BankRepository;
import com.employee.repo.GradeRepository;
import com.employee.service.BankService;

@Service
public class BankServiceImpl implements BankService{
	
	@Autowired BankRepository bankRepo;
	@Autowired GradeRepository gradeRepo;

	@Override
	public BankEntity setBalanceAccount(Double account) {
		BankEntity entity = new BankEntity(account);
		Double transferAmount = transferAmount();
		Double requiredBalance = Math.max(0, transferAmount-entity.getBankBalance());
		entity.setRequiredBalance(requiredBalance);
		bankRepo.save(entity);
		return entity;
	}

	@Override
	public BankEntity getBalanceAccount() {
		
		if(bankRepo.existsById(1))
			return bankRepo.getOne(1);
		else
			return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Double transferAmount() 
	{
		List<GradeEntity> list = gradeRepo.findAll();
		Double amount=0d;
		Double salary=0d;
		for(int i=0;i<list.size();i++)
		{
			GradeEntity entity = list.get(i);
			salary = entity.getBasicSalary()+entity.getHouseRent()+entity.getMedicalAllowance();
			amount = amount+entity.getEmployeeNumber()*salary;
		}
		return amount;
	}

}
