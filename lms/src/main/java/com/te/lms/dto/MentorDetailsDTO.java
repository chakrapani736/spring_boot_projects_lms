package com.te.lms.dto;

import java.util.List;

import com.te.lms.entity.BatchDetails;
import com.te.lms.entity.EmployeePrimaryInfo;
import com.te.lms.entity.MockDetails;

import lombok.Data;

@Data
public class MentorDetailsDTO {
	private int mentorNo;
	private String mentorId;
	private String mentorName;
	private String mentorGender;
	private String mentorRole;
	private String mentorEmail;		//userName
	private String mentorSkils;
	private String password;
	
	private List<BatchDetails> batchDetails;
	
	private List<MockDetails> mockDetails;

}
