package com.te.lms.dto;

import java.sql.Date;
import java.util.List;

import com.te.lms.entity.EmployeePrimaryInfo;
import com.te.lms.entity.MentorDetails;
import com.te.lms.entity.MockDetails;

import lombok.Data;

@Data
public class BatchDetailsDTO {
	private int batchNo;
	private String batchId;
	private String batchName;
	private String batchTechnology;
	private Date batchStartDate;
	private Date batchEndDate;
	private String batchStatus;
	private int batchStrength;
	private int mentorNo;
	private List<Integer> employeeIds;

	private List<EmployeePrimaryInfo> employeeInfo;

	private MentorDetails mentor;

	private List<MockDetails> mocks;

}
