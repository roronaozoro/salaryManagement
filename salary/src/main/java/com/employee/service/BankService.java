package com.employee.service;

import com.employee.entity.BankEntity;

public interface BankService {
	
	public BankEntity setBalanceAccount(Double account);
	
	public BankEntity getBalanceAccount();
	
	public Double transferAmount();
	
}
