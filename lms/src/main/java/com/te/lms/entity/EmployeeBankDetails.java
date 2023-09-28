package com.te.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "employee_bank_detail")

public class EmployeeBankDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeBankDetailId;
	@NotBlank(message = "Bank Name should not be empty")
	private String employeeBankName;
	@Min(value = 6, message = "Account number contains atleast 6 numbers")
	private long employeeBankAccountNo;
	private long employeeBankAccountIfscCode;
	private String employeeBankAccountType;
	private String employeeBankAccountBarnchName;
	private String employeeBankAccountBranchState;

}
