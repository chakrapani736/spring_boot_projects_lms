package com.te.lms.dto;

import lombok.Data;

@Data
public class EmployeeRequestedDTO {
	private int employeeNo;
	private String employeeId;
	private String employeeName;
	private int employeeYearOfPassOut;
	private String employeePercentage;
	private double employeeYearOfExperience;
	private long employeeContactNumber;
	private String batchName;
}
