package com.te.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="employee_technicalskills_info")

public class EmployeeTechnicalSkillsInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeSkillNo;
	private String employeeSkillType;
	private String employeeSkillRatings;
	private double employeeYearOfExperinceOverSkill;
}

