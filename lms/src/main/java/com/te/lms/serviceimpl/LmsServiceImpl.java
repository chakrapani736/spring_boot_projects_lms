package com.te.lms.serviceimpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.te.lms.dto.BatchDetailsDTO;
import com.te.lms.dto.EmployeePrimaryInfoDTO;
import com.te.lms.dto.EmployeeRequestedDTO;
import com.te.lms.dto.MailRejectionDTO;
import com.te.lms.dto.MentorDetailsDTO;
import com.te.lms.dto.MockDetailsDTO;
import com.te.lms.dto.MockRatingDTO;
import com.te.lms.entity.BatchDetails;
import com.te.lms.entity.EmployeePrimaryInfo;
import com.te.lms.entity.MentorDetails;
import com.te.lms.entity.MockDetails;
import com.te.lms.entity.MockRating;
import com.te.lms.exception.BatchNotFoundException;
import com.te.lms.exception.EmailNotFoundException;
import com.te.lms.exception.EmployeeNotFoundException;
import com.te.lms.exception.MentorNotFoundException;
import com.te.lms.exception.MockNotFoundException;
import com.te.lms.repository.LmsBatchRepository;
import com.te.lms.repository.LmsEmployeeRepository;
import com.te.lms.repository.LmsMentorRepository;
import com.te.lms.repository.LmsMockRatingRepository;
import com.te.lms.repository.LmsMockRepository;
import com.te.lms.securityconfig.AutoGeneratePassword;
import com.te.lms.service.LmsService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class LmsServiceImpl implements LmsService {

	@Autowired
	private LmsEmployeeRepository lmsEmployeeRepository;

	@Autowired
	private LmsMentorRepository lmsMentorRepository;

	@Autowired
	private LmsBatchRepository lmsbatchRepository;

	@Autowired
	private LmsMockRepository lmsmockRepository;

	@Autowired
	private LmsMockRatingRepository lmsratingRepository;

	@Autowired
	private AutoGeneratePassword generatePsw;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender mailSender;

	private String email, userName, password, reason, person;

	public void sendEmail(String person, String email, String userName, String password) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(email);
		helper.setSubject("Reset Password");
		helper.setText("Dear " + person + ",\nGreetings of the Day! \nYour Current Login Credentials \n username : "
				+ userName + "\n password : " + password);
		mailSender.send(message);
	}

	private void sendRejectionEmail(String email, String reason) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(email);
		helper.setSubject("Application Rejected");
		helper.setText("Dear Applicant, \n      " + reason + "\nTry next time. \nThank You.");
		mailSender.send(message);
	}

	private void sendUpdationEmail(String email, Date date) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(email);
		helper.setSubject("welcome to TESTYANTRA");
		helper.setText("Dear Applicant,\n your date of joining is : " + date
				+ "\nThank You. \n do report with original documents at jayanagar,Bangalore");
		mailSender.send(message);
	}

	@Override
	public EmployeePrimaryInfoDTO sendEmployeeInfo(EmployeePrimaryInfoDTO primaryInfoDTO)
			throws EmployeeNotFoundException {
		EmployeePrimaryInfo employeePrimaryInfo = new EmployeePrimaryInfo();
		BeanUtils.copyProperties(primaryInfoDTO, employeePrimaryInfo);
		if (primaryInfoDTO != null) {
			lmsEmployeeRepository.save(employeePrimaryInfo);
			return primaryInfoDTO;
		} else {
			throw new EmployeeNotFoundException("employee data not present");
		}
	}

	@Override
	public EmployeePrimaryInfoDTO approveById(int employeeNo, EmployeePrimaryInfoDTO primaryInfoDTO)
			throws EmployeeNotFoundException, MessagingException {
		EmployeePrimaryInfo employee = lmsEmployeeRepository.findById(employeeNo)
				.orElseThrow(() -> new EmployeeNotFoundException("employee data is not present"));
		email = employee.getEmployeeEmail();
		password = generatePsw.generatePassword(8);
		userName = employee.getEmployeeEmail();
		person = employee.getEmployeeName();
		employee.setEmployeeStatus("Active");
		employee.setEmployeeId(generatePsw.generateId());
		employee.setEmployeeDesignation(primaryInfoDTO.getEmployeeDesignation());
		employee.setEmployeeRole("ROLE_" + primaryInfoDTO.getEmployeeRole().toUpperCase());
		employee.setPassword(passwordEncoder.encode(password));
		sendEmail(person, email, userName, password);
		lmsEmployeeRepository.save(employee);
		return primaryInfoDTO;
	}

	@Override
	public MailRejectionDTO rejectEmployee(int id, MailRejectionDTO rejectionDTO)
			throws MessagingException, EmployeeNotFoundException {
		EmployeePrimaryInfo employee = lmsEmployeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("employee not found"));
		email = employee.getEmployeeEmail();
		reason = rejectionDTO.getReasonOfReject();
		employee.setEmployeeStatus("Rejected");
		sendRejectionEmail(email, reason);
		lmsEmployeeRepository.save(employee);
		return rejectionDTO;
	}

	@Override
	public EmployeePrimaryInfoDTO resetPassword(EmployeePrimaryInfoDTO primaryInfoDTO) throws EmailNotFoundException {
		EmployeePrimaryInfo employeePrimaryInfo = lmsEmployeeRepository
				.findByemployeeEmail(primaryInfoDTO.getEmployeeEmail());
		if (employeePrimaryInfo != null) {
			employeePrimaryInfo.setPassword(passwordEncoder.encode(primaryInfoDTO.getPassword()));
			lmsEmployeeRepository.save(employeePrimaryInfo);
			return primaryInfoDTO;
		} else {
			throw new EmailNotFoundException("provide a valid email");
		}
	}

	@Override
	public List<EmployeeRequestedDTO> getAllRequestedEmployeeData() throws EmployeeNotFoundException {
		List<EmployeePrimaryInfo> listOfEmployee = lmsEmployeeRepository.findAll();
		if (listOfEmployee != null) {
			List<EmployeeRequestedDTO> list = new ArrayList<>();
			listOfEmployee.forEach(employee -> {
				if (employee.getEmployeeStatus() == null) {
					EmployeeRequestedDTO DTO = new EmployeeRequestedDTO();
					DTO.setEmployeeNo(employee.getEmployeeNo());
					DTO.setEmployeeName(employee.getEmployeeName());
					DTO.setEmployeePercentage(employee.getEducationInfo().get(0).getEmployeePercentage());
					DTO.setEmployeeYearOfPassOut(employee.getEducationInfo().get(0).getEmployeeYearOfPassOut());
					DTO.setEmployeeYearOfExperience(employee.getExperienceInfo().get(0).getEmployeeYearOfExperience());
					DTO.setEmployeeContactNumber(employee.getContactInfo().get(0).getEmployeeConatctNumber());
					list.add(DTO);
				}
			});
			return list;
		} else {
			throw new EmployeeNotFoundException("employee not found in data base");
		}
	}

	@Override
	public EmployeePrimaryInfoDTO getEmployeeByUserId(int id) throws EmployeeNotFoundException {
		EmployeePrimaryInfo employee = lmsEmployeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("employee not found for this id"));
		EmployeePrimaryInfoDTO dto = new EmployeePrimaryInfoDTO();
		BeanUtils.copyProperties(employee, dto);
		return dto;
	}

	@Override
	public List<EmployeePrimaryInfoDTO> getAllEmployeeData() throws EmployeeNotFoundException {
		List<EmployeePrimaryInfo> allEmployee = lmsEmployeeRepository.findAll();
		if (allEmployee != null) {
			List<EmployeePrimaryInfoDTO> allEmployeeDTO = new ArrayList<>();
			allEmployee.forEach(employee -> {
				EmployeePrimaryInfoDTO primaryInfoDTO = new EmployeePrimaryInfoDTO();
				primaryInfoDTO.setEmployeeId(employee.getEmployeeId());
				primaryInfoDTO.setEmployeeNo(employee.getEmployeeNo());
				primaryInfoDTO.setEmployeeName(employee.getEmployeeName());
				primaryInfoDTO.setEmployeeGender(employee.getEmployeeGender());
				primaryInfoDTO.setEmployeeDateOfBirth(employee.getEmployeeDateOfBirth());
				primaryInfoDTO.setEmployeeBloodGroup(employee.getEmployeeBloodGroup());
				primaryInfoDTO.setEmployeeEmail(employee.getEmployeeEmail());
				primaryInfoDTO.setEmployeeDateOfJoining(employee.getEmployeeDateOfJoining());
				primaryInfoDTO.setEmployeeDesignation(employee.getEmployeeDesignation());
				primaryInfoDTO.setEmployeeNationality(employee.getEmployeeNationality());
				primaryInfoDTO.setEmployeeRole(employee.getEmployeeRole());
				primaryInfoDTO.setEmployeeStatus(employee.getEmployeeStatus());
				primaryInfoDTO.setAddressInfo(employee.getAddressInfo());
				primaryInfoDTO.setBankDetails(employee.getBankDetails());
				primaryInfoDTO.setContactInfo(employee.getContactInfo());
				primaryInfoDTO.setEducationInfo(employee.getEducationInfo());
				primaryInfoDTO.setExperienceInfo(employee.getExperienceInfo());
				primaryInfoDTO.setSkillsInfo(employee.getSkillsInfo());
				allEmployeeDTO.add(primaryInfoDTO);
			});
			return allEmployeeDTO;
		} else {
			throw new EmployeeNotFoundException("employee data not present in data base");
		}
	}

	@Override
	public EmployeePrimaryInfoDTO deleteByID(int employeeId) throws EmployeeNotFoundException, BatchNotFoundException {
		EmployeePrimaryInfo primaryInfo = lmsEmployeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("employee data not found"));
		EmployeePrimaryInfoDTO primaryInfoDTO = new EmployeePrimaryInfoDTO();
		BeanUtils.copyProperties(primaryInfo, primaryInfoDTO);
		lmsEmployeeRepository.deleteById(employeeId);
		return primaryInfoDTO;
	}

	@Override
	public MentorDetailsDTO addMentors(MentorDetailsDTO mentorDetailsDTO)
			throws MessagingException, MentorNotFoundException {
		MentorDetails mentors = new MentorDetails();
		BeanUtils.copyProperties(mentorDetailsDTO, mentors);
		if (mentorDetailsDTO != null) {
			email = mentors.getMentorEmail();
			password = generatePsw.generatePassword(8);
			userName = mentors.getMentorEmail();
			person = mentorDetailsDTO.getMentorName();
			mentors.setMentorRole("ROLE_MENTOR");
			mentors.setMentorId(generatePsw.generateId());
			mentors.setPassword(passwordEncoder.encode(password));
			sendEmail(person, email, userName, password);
			lmsMentorRepository.save(mentors);
			return mentorDetailsDTO;
		} else {
			throw new MentorNotFoundException("montor details are not available");
		}
	}

	@Override
	public MentorDetailsDTO resetMentorPassword(MentorDetailsDTO mentorDetailsDTO) throws EmailNotFoundException {
		MentorDetails mentorDetails = lmsMentorRepository.findBymentorEmail(mentorDetailsDTO.getMentorEmail());
		if (mentorDetails != null) {
			mentorDetails.setPassword(passwordEncoder.encode(mentorDetailsDTO.getPassword()));
			lmsMentorRepository.save(mentorDetails);
			return mentorDetailsDTO;
		} else {
			throw new EmailNotFoundException("provide a valid email");
		}
	}

	@Override
	public List<MentorDetailsDTO> getAllMentorDetail() throws MentorNotFoundException {
		List<MentorDetails> allMentor = lmsMentorRepository.findAll();
		if (allMentor != null) {
			List<MentorDetailsDTO> mentorDTOs = new ArrayList<>();
			allMentor.forEach(mentor -> {
				MentorDetailsDTO detailsDTO = new MentorDetailsDTO();
				detailsDTO.setMentorNo(mentor.getMentorNo());
				detailsDTO.setMentorId(mentor.getMentorId());
				detailsDTO.setMentorName(mentor.getMentorName());
				detailsDTO.setMentorRole(mentor.getMentorRole());
				detailsDTO.setMentorGender(mentor.getMentorGender());
				detailsDTO.setMentorEmail(mentor.getMentorEmail());
				detailsDTO.setMentorSkils(mentor.getMentorSkils());
				mentorDTOs.add(detailsDTO);
			});
			return mentorDTOs;
		} else {
			throw new MentorNotFoundException("mentor data is not present in data base");
		}
	}

	@Override
	public MentorDetailsDTO updateMentorDetails(int mentorNo, MentorDetailsDTO mentorDetailsDTO)
			throws MentorNotFoundException {
		MentorDetails mentor = lmsMentorRepository.findById(mentorNo).orElseThrow(
				() -> new MentorNotFoundException("mentor not present with this Id ... please provide valid Id...!"));
		mentor.setMentorName(mentorDetailsDTO.getMentorName());
		mentor.setMentorEmail(mentorDetailsDTO.getMentorEmail());
		mentor.setMentorGender(mentorDetailsDTO.getMentorGender());
		mentor.setMentorSkils(mentorDetailsDTO.getMentorSkils());
		lmsMentorRepository.save(mentor);
		return mentorDetailsDTO;
	}

	@Override
	public MentorDetailsDTO deleteMentorByID(int mentorId) throws MentorNotFoundException {
		MentorDetails mentor = lmsMentorRepository.findById(mentorId).orElseThrow(() -> new MentorNotFoundException(
				"Mentor Data Not Present with This ID ...\n please provide valid Id...!"));
		MentorDetailsDTO mentorDTO = new MentorDetailsDTO();
		BeanUtils.copyProperties(mentor, mentorDTO);
		lmsMentorRepository.delete(mentor);
		return mentorDTO;
	}

	@Override
	public BatchDetailsDTO addBatches(BatchDetailsDTO batchDetailsDTO)
			throws BatchNotFoundException, MessagingException, MentorNotFoundException, EmployeeNotFoundException {
		if (batchDetailsDTO != null) {
			BatchDetails batchDetails = new BatchDetails();

			// Fetch the existing mentor by mentorId
			MentorDetails mentor = lmsMentorRepository.findById(batchDetailsDTO.getMentorNo())
					.orElseThrow(() -> new MentorNotFoundException("Mentor Not Found!"));

			// Fetch the existing employee by employeeIds
			List<EmployeePrimaryInfo> employees = lmsEmployeeRepository.findAllById(batchDetailsDTO.getEmployeeIds());
			// Creating an Empty List to store valid employees
			List<EmployeePrimaryInfo> validEmployees = new ArrayList<>();

			employees.forEach(employee -> {
				if (employee.getEmployeeStatus() != null && employee.getEmployeeStatus().equals("Active")) {
					validEmployees.add(employee);
				}
			});

			if (validEmployees.isEmpty())
				throw new BatchNotFoundException("Valid Employee not found for Batch Creation");
			else {
				BeanUtils.copyProperties(batchDetailsDTO, batchDetails);
				batchDetails.setBatchId("#0" + generatePsw.generateId());
				batchDetails.setBatchStrength(validEmployees.size());
				batchDetails.setMentor(mentor);
				batchDetails.setEmployeeInfo(validEmployees);
				lmsbatchRepository.save(batchDetails);

				validEmployees.forEach(employee -> {
					employee.setEmployeeDateOfJoining(batchDetailsDTO.getBatchStartDate());
					lmsEmployeeRepository.save(employee);
				});
				return batchDetailsDTO;
			}
		} else
			throw new EmployeeNotFoundException("Data Not Found for Store!");
	}

	@Override
	public List<BatchDetailsDTO> getAllBatchDetail() throws BatchNotFoundException {
		List<BatchDetails> batchDetailsList = lmsbatchRepository.findAll();
		if (batchDetailsList != null) {
			List<BatchDetailsDTO> detailsDTOs = new ArrayList<>();
			batchDetailsList.forEach(batch -> {
				BatchDetailsDTO batchDetailsDTO = new BatchDetailsDTO();
				batchDetailsDTO.setBatchNo(batch.getBatchNo());
				batchDetailsDTO.setBatchId(batch.getBatchId());
				batchDetailsDTO.setBatchName(batch.getBatchName());
				batchDetailsDTO.setBatchTechnology(batch.getBatchTechnology());
				batchDetailsDTO.setBatchStatus(batch.getBatchStatus());
				batchDetailsDTO.setBatchStartDate(batch.getBatchStartDate());
				batchDetailsDTO.setBatchEndDate(batch.getBatchEndDate());
				batchDetailsDTO.setBatchStrength(batch.getBatchStrength());
				batchDetailsDTO.setMentorNo(batch.getMentor().getMentorNo());

				detailsDTOs.add(batchDetailsDTO);
			});
			return detailsDTOs;
		} else {
			throw new BatchNotFoundException("batch data is not present");
		}
	}

	@Override
	public BatchDetailsDTO updateBatchDetail(int batchNo, BatchDetailsDTO batchDetailsDTO)
			throws BatchNotFoundException {
		BatchDetails batch = lmsbatchRepository.findById(batchNo)
				.orElseThrow(() -> new BatchNotFoundException("Batch not present ... \n please provide valid id"));
		batch.setBatchName(batchDetailsDTO.getBatchName());
		batch.setBatchId(generatePsw.generateId());
		batch.setBatchStartDate(batchDetailsDTO.getBatchStartDate());
		batch.setBatchEndDate(batchDetailsDTO.getBatchEndDate());
		batch.setBatchTechnology(batchDetailsDTO.getBatchTechnology());
		batch.setBatchStatus(batchDetailsDTO.getBatchStatus());
		batch.setBatchStrength(batchDetailsDTO.getBatchStrength());
		lmsbatchRepository.save(batch);
		return batchDetailsDTO;
	}

	@Override
	public BatchDetailsDTO deleteBatchByID(int batchId) throws BatchNotFoundException {
		BatchDetails batch = lmsbatchRepository.findById(batchId)
				.orElseThrow(() -> new BatchNotFoundException("please provide valid Id"));
		BatchDetailsDTO batchDTO = new BatchDetailsDTO();
		BeanUtils.copyProperties(batch, batchDTO);
		lmsbatchRepository.delete(batch);
		return batchDTO;
	}

	@Override
	public MockDetailsDTO addMockInformation(MockDetailsDTO mockDetailsDTO, int mentorId, int batchId)
			throws MockNotFoundException {
		MockDetails mocks = new MockDetails();
		MentorDetails mentorDetails = lmsMentorRepository.findById(mentorId).get();
		BatchDetails batchDetails = lmsbatchRepository.findById(batchId).get();
		BeanUtils.copyProperties(mockDetailsDTO, mocks);
		if (mockDetailsDTO != null) {
			mocks.setPanel(Arrays.asList(mentorDetails));
			mocks.setBatchDetail(batchDetails);
			lmsmockRepository.save(mocks);
			return mockDetailsDTO;
		} else {
			throw new MockNotFoundException("mock data not available");
		}
	}

	@Override
	public EmployeePrimaryInfoDTO giveAttendance(int id) throws EmployeeNotFoundException {
		EmployeePrimaryInfo employee = lmsEmployeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("employee data not found"));
		int attendance = employee.getEmployeeAttendance();
		employee.setEmployeeAttendance(++attendance);
		EmployeePrimaryInfoDTO dto = new EmployeePrimaryInfoDTO();
		BeanUtils.copyProperties(employee, dto);
		if (employee.getEmployeeStatus() != null && employee.getEmployeeStatus().equals("Active")) {
			lmsEmployeeRepository.save(employee);
			return dto;

		} else {
			throw new EmployeeNotFoundException("employee is not approved");
		}
	}

	@Override
	public MockRatingDTO giveRatings(MockRatingDTO mockRatingDTO, int id)
			throws MockNotFoundException, EmployeeNotFoundException {
		MockRating rating = new MockRating();
		EmployeePrimaryInfo employeePrimaryInfo = lmsEmployeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("employee not found with this id"));
		BeanUtils.copyProperties(mockRatingDTO, rating);
		if (mockRatingDTO != null) {
			lmsratingRepository.save(rating);
			mockRatingDTO.setEmployees(Arrays.asList(employeePrimaryInfo));
			return mockRatingDTO;
		} else {
			throw new MockNotFoundException("data is not present");
		}
	}

	@Override
	public List<EmployeeRequestedDTO> getAllApprovedEmployeeData() throws EmployeeNotFoundException {
		List<EmployeePrimaryInfo> listOfEmployee = lmsEmployeeRepository.findAll();
		if (listOfEmployee != null) {
			List<EmployeeRequestedDTO> list = new ArrayList<>();
			listOfEmployee.forEach(employee -> {
				if (employee.getEmployeeStatus() != null && !employee.getEmployeeStatus().contains("Rejected")) {
					EmployeeRequestedDTO DTO = new EmployeeRequestedDTO();
					DTO.setEmployeeNo(employee.getEmployeeNo());
					DTO.setEmployeeId(employee.getEmployeeId());
					DTO.setEmployeeName(employee.getEmployeeName());
					DTO.setEmployeePercentage(employee.getEducationInfo().get(0).getEmployeePercentage());
					DTO.setEmployeeYearOfPassOut(employee.getEducationInfo().get(0).getEmployeeYearOfPassOut());
					DTO.setEmployeeYearOfExperience(employee.getExperienceInfo().get(0).getEmployeeYearOfExperience());
					DTO.setEmployeeContactNumber(employee.getContactInfo().get(0).getEmployeeConatctNumber());
					list.add(DTO);
				}
			});
			return list;
		} else {
			throw new EmployeeNotFoundException("employee data not present");
		}
	}

}
