package com.employee.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bankAcc")
public class BankEntity 
{
    @Id
    private int id;
    
    private Double bankBalance;

	private Double requiredBalance;
	
	public BankEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public BankEntity(Double bankBalance) {
		super();
		this.id = 1;
		this.bankBalance = bankBalance;
		this.requiredBalance=0d;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Double getBankBalance() {
		return bankBalance;
	}



	public void setBankBalance(Double bankBalance) {
		this.bankBalance = bankBalance;
	}


	public Double getRequiredBalance() {
		return requiredBalance;
	}

	public void setRequiredBalance(Double requiredBalance) {
		this.requiredBalance = requiredBalance;
	}

	@Override
	public String toString() {
		return "BankAccountEntity [id=" + id + ", bankBalance=" + bankBalance + "]";
	}
  
    
}
