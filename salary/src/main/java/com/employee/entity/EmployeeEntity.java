package com.employee.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="emp")
public class EmployeeEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer empId;
	
	private String empName;
	
	private Integer grade;
	
	private String address;
	
	private String mobile;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "acc_id", referencedColumnName = "acc_id")
	private AccountEntity account;

	public Integer getEmpId() {
		return empId;
	}


	public void setEmpId(Integer empId) {
		this.empId = empId;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public Integer getGrade() {
		return grade;
	}


	public void setGrade(Integer grade) {
		this.grade = grade;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public AccountEntity getAccount() {
		return account;
	}


	public void setAccount(AccountEntity account) {
		this.account = account;
	}


	@Override
	public String toString() {
		return "EmployeeEntity [empId=" + empId + ", empName=" + empName + ", gradeId=" + grade + ", address="
				+ address + ", mobile=" + mobile + ", account=" + account + "]";
	}

	
	
}
