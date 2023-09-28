package com.te.lms.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "employee_primary_Info", uniqueConstraints = @UniqueConstraint(columnNames = { "employeeEmail" }))

public class EmployeePrimaryInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeNo;
	private String employeeId;
	@NotNull(message = "employee name should not be empty")
	private String employeeName;
	@NotNull(message = "employee gender should not be empty")
	private String employeeGender;
	private Date employeeDateOfBirth;
	private Date employeeDateOfJoining;
	@Email(message = "it should be in email type")
	private String employeeEmail; // userName
	private String employeeBloodGroup;
	private String employeeDesignation;
	private String employeeNationality;
	private String employeeStatus;
	private int employeeAttendance;
	private String employeeRole;
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	private EmployeeSecondaryInfo secondaryInfo;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_no", referencedColumnName = "employeeNo")
	private List<EmployeeEducationInfo> educationInfo;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_no", referencedColumnName = "employeeNo")
	private List<EmployeeTechnicalSkillsInfo> skillsInfo;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_no", referencedColumnName = "employeeNo")
	private List<EmployeeExperienceInfo> experienceInfo;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_no", referencedColumnName = "employeeNo")
	private List<EmployeeBankDetails> bankDetails;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_no", referencedColumnName = "employeeNo")
	private List<EmployeeAddressInfo> addressInfo;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_no", referencedColumnName = "employeeNo")
	private List<EmployeeContactInfo> contactInfo;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_no", referencedColumnName = "employeeNo")
	private List<MockRating> mockRatings;
}
