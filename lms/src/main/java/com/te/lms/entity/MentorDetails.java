package com.te.lms.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import lombok.Data;
@Entity
@Data
@Table(name="mentor_details" , uniqueConstraints = @UniqueConstraint(columnNames = { "mentorEmail" }))

public class MentorDetails {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int mentorNo;
	private String mentorId;
	private String mentorName;
	private String mentorGender;
	@Email(message = "mail should be in email type")
	private String mentorEmail;		//userName
	private String mentorSkils;
	private String mentorRole;
	private String password;
	
	@OneToMany(mappedBy = "mentor",cascade = CascadeType.ALL)
	private List<BatchDetails> batchDetails;
	
	
	@ManyToMany(mappedBy = "panel",cascade = CascadeType.ALL)
	private List<MockDetails> mockDetails;
}
