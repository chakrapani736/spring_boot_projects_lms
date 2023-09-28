package com.te.lms.dto;

import lombok.Data;

@Data
public class EmployeeBankDetailsDTO {
	private int employeeBankDetailId;
	private  String employeeBankName;
	private long employeeBankAccountNo;
	private long employeeBankAccountIfscCode;
	private String employeeBankAccountType;
	private String employeeBankAccountBarnchName;
	private String employeeBankAccountBranchState;
	
}
