package com.employee.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="grade")
public class GradeEntity 
{
	@Id
	@Column(name= "grade_id")
	private Integer gradeId;
	
	@OneToMany(mappedBy = "grade")
	private List<EmployeeEntity> employee;
	
	private int employeeNumber;
	
	private double basicSalary;
		
	private Double houseRent;
	
	private Double medicalAllowance;
	
	public GradeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GradeEntity(Integer gradeId, int employeeNumber, double basicSalary) {
		super();
		this.gradeId = gradeId;
		this.employeeNumber = employeeNumber;
		this.basicSalary = basicSalary;
		this.houseRent = basicSalary*0.2;
		this.medicalAllowance = basicSalary*0.15;
	}
	
	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Double getHouseRent() {
		return houseRent;
	}

	public void setHouseRent(Double houseRent) {
		this.houseRent = houseRent;
	}

	public Double getMedicalAllowance() {
		return medicalAllowance;
	}

	public void setMedicalAllowance(Double medicalAllowance) {
		this.medicalAllowance = medicalAllowance;
	}

	@Override
	public String toString() {
		return "GradeEntity [gradeId=" + gradeId + ", employeeNumber=" + employeeNumber + ", basicSalary=" + basicSalary
				+ ", houseRent=" + houseRent + ", medicalAllowance=" + medicalAllowance + "]";
	}

}
