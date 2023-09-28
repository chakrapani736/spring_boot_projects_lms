package com.te.lms.dto;

import lombok.Data;

@Data
public class EmployeeAddressInfoDTO {
	private int employeeAddressNo; 
	private String employeeAddressType;
	private String employeeDoorNo;
	private String employeeStreet;
	private String employeeCity;
	private String employeeState;
	private int employeePinCode;
	private String employeeLandMark;
}
