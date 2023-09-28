package com.te.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "employee_contact_info")

public class EmployeeContactInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeContactDeatilNo;
	private String employeeConatctType;
	private long employeeConatctNumber;
}
