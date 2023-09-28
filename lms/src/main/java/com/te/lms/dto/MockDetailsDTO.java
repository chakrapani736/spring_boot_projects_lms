package com.te.lms.dto;

import java.util.Date;
import java.util.List;

import com.te.lms.entity.BatchDetails;
import com.te.lms.entity.MentorDetails;

import lombok.Data;

@Data
public class MockDetailsDTO {
	private int mockNo;
	private String technology;
	private Date mockDateTime;

	private List<MentorDetails> panel;

	private BatchDetails batchDetail;

}
