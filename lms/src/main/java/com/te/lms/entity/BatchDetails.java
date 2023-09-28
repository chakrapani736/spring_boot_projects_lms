package com.te.lms.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "batch_details")
public class BatchDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int batchNo;
	private String batchId;
	@NotNull(message = "batchName should not be empty")
	private String batchName;
	@NotBlank(message = "atleast one string variable should be there")
	private String batchTechnology;
	private Date batchStartDate;
	private String batchStatus;
	private Date batchEndDate;
	private int batchStrength;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "batch_no", referencedColumnName = "batchNo")
	private List<EmployeePrimaryInfo> employeeInfo;

	@ManyToOne(cascade = CascadeType.ALL)
	private MentorDetails mentor;

	@OneToMany(mappedBy = "batchDetail", cascade = CascadeType.ALL)
	private List<MockDetails> mocks;
}
