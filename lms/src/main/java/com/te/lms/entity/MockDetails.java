package com.te.lms.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="mock_details")

public class MockDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mockNo;
	private String technology;
	private Date mockDateTime;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "mock_takenby_panel_member",joinColumns = {@JoinColumn(name="mock_No",referencedColumnName = "mockNo")},inverseJoinColumns = {@JoinColumn(name="panel_Id",referencedColumnName = "mentorNo")})
	private List<MentorDetails> panel;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private BatchDetails batchDetail;

}
