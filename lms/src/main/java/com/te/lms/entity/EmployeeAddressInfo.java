package com.te.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Data
@Table(name = "employee_address_info")

public class EmployeeAddressInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeAddressNo;
	private String employeeAddressType;
	private String employeeDoorNo;
	private String employeeStreet;
	private String employeeCity;
	private String employeeState;
	@Min(value = 6, message = "it should be min 6 numbers")
	private int employeePinCode;
	private String employeeLandMark;

}
