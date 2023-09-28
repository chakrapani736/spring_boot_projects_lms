package com.te.lms.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "mock_rating")
@Data

public class MockRating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mockInfoNo;
	private String mockType;
	private String mockTakenBy;
	private String mockTechnology;
	private int practicalKnoledge;
	private int technicalKnoledge;
	private String overAllFeedBack;
	private String detailedFeedBack;

}
