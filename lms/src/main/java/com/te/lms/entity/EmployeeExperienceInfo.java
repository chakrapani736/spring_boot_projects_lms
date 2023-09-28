package com.te.lms.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name="employee_experience_info")
 
public class EmployeeExperienceInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeExperienceDetailNo;
	@NotBlank(message = "company name should not be blank")
	private String employeeCompnyName;
	private int employeeYearOfExperience;
	private Date employeeDateOfJoining;
	private Date employeeDateOfRelieving;
	private String employeeDesignation;
	private String employeeCompanyLocation;

}
