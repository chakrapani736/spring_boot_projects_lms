package com.te.lms.dto;

import java.util.List;

import com.te.lms.entity.EmployeePrimaryInfo;

import lombok.Data;

@Data
public class MockRatingDTO {
	private int mockInfoNo;
	private String mockType;
	private String mockTakenBy;
	private String mockTechnology;
	private int practicalKnoledge;
	private int technicalKnoledge;
	private String overAllFeedBack;
	private String detailedFeedBack;
	
	private List<EmployeePrimaryInfo> employees;

}
