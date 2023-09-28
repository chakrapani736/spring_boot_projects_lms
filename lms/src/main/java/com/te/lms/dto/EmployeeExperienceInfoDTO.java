package com.te.lms.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class EmployeeExperienceInfoDTO {
	private int employeeExperienceDetailNo;
	private String employeeCompnyName;
	private int employeeYearOfExperience;
	private Date employeeDateOfJoining;
	private Date employeeDateOfRelieving;
	private String employeeDesignation;
	private String employeeCompanyLocation;

}
