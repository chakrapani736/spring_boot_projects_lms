package com.te.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "employee_secondary_info")

public class EmployeeSecondaryInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeInfoId;
	private String employeePanNumber;
	private long employeeAdharNumber;
	private String employeeFatherName;
	private String employeeMotherName;
	private String employeeSpouseName;
	private String employeePassportNumber;
	private String employeeMaritalStatus;

}
