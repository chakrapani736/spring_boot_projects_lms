package com.te.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name="employee_education_info")

public class EmployeeEducationInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeEducationInfoNo;
	private String employeeEducationType;
	@NotNull(message = "YOP should not be null")
	private int employeeYearOfPassOut;
	private String employeeUniversityName;
	private String employeeInstituteName;
	private String employeePercentage;
	@NotNull(message = "employee specialization should not be empty")
	private String employeeSpecialization;
	private String employeeEducationState;


}
