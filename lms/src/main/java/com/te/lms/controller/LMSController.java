package com.te.lms.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.BatchDetailsDTO;
import com.te.lms.dto.EmployeePrimaryInfoDTO;
import com.te.lms.dto.EmployeeRequestedDTO;
import com.te.lms.dto.MailRejectionDTO;
import com.te.lms.dto.MentorDetailsDTO;
import com.te.lms.dto.MockDetailsDTO;
import com.te.lms.dto.MockRatingDTO;
import com.te.lms.exception.BatchNotFoundException;
import com.te.lms.exception.EmailNotFoundException;
import com.te.lms.exception.EmployeeNotFoundException;
import com.te.lms.exception.MentorNotFoundException;
import com.te.lms.exception.MockNotFoundException;
import com.te.lms.responce.LmsResponse;
import com.te.lms.service.LmsService;

import jakarta.mail.MessagingException;

@RestController
public class LMSController {

	@Autowired
	private LmsService lmsService;// Has-a relationship

	@Autowired
	private LmsResponse response;// Has-a relationship

	// Employee_Controller Details

	// URL for sending employee data to Admin

	@PostMapping("/employeeApply")
	public ResponseEntity<LmsResponse> sendEmployeeData(@RequestBody EmployeePrimaryInfoDTO primaryInfoDTO)
			throws EmployeeNotFoundException {
		EmployeePrimaryInfoDTO infoDTO = lmsService.sendEmployeeInfo(primaryInfoDTO);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data added Succesfully..!");
		response.setLmsList(Arrays.asList(infoDTO));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}

	// Admin_Controller Details

	// Approval and Rejection

	// URL for updating details of an employee after approval

	@PutMapping("approve/{id}")
	public ResponseEntity<LmsResponse> approveEmployee(@PathVariable("id") int employeeNo,
			@RequestBody EmployeePrimaryInfoDTO primaryInfoDTO) throws EmployeeNotFoundException, MessagingException {
		EmployeePrimaryInfoDTO infoDTO = lmsService.approveById(employeeNo, primaryInfoDTO);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data updated Succesfully after approval..!");
		response.setLmsList(Arrays.asList(infoDTO));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}

	// URL for giving reason for rejecting employee

	@PutMapping("rejectEmployee/{id}")
	public ResponseEntity<LmsResponse> rejectEmployee(@PathVariable("id") int id,
			@RequestBody MailRejectionDTO rejectionDTO) throws MessagingException, EmployeeNotFoundException {
		MailRejectionDTO infoDTO = lmsService.rejectEmployee(id, rejectionDTO);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Rejected ..!");
		response.setLmsList(Arrays.asList(infoDTO));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);

	}
	// URL for Setting password by employee through his email

	@PutMapping("/resetPasswordEmployee")
	public ResponseEntity<LmsResponse> resetPassword(@RequestBody EmployeePrimaryInfoDTO primaryInfoDTO)
			throws EmailNotFoundException {
		EmployeePrimaryInfoDTO infoDTO = lmsService.resetPassword(primaryInfoDTO);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Password Changed");
		response.setLmsList(Arrays.asList(infoDTO));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.ACCEPTED);
	}

	// URL for getting all employee data who is not declared his status by Admin

	@GetMapping("/getRequestedEmployee")
	public ResponseEntity<LmsResponse> getAllRequestedEmployee() throws EmployeeNotFoundException {
		List<EmployeeRequestedDTO> employeeList = lmsService.getAllRequestedEmployeeData();
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data fetched Succesfully..!");
		response.setLmsList(Arrays.asList(employeeList));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}

	// URL for fetching employee data by id

	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<LmsResponse> getEmployeeProfileDetails(@PathVariable("id") int id) throws EmployeeNotFoundException{
		EmployeePrimaryInfoDTO infoDTO = lmsService.getEmployeeByUserId(id);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data fetched Succesfully..!");
		response.setLmsList(Arrays.asList(infoDTO));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}

	// Getting All Employee Data By Admin
	@GetMapping("/getAllEmployee")
	public ResponseEntity<LmsResponse> getAllEmployee() throws EmployeeNotFoundException {
		List<EmployeePrimaryInfoDTO> employeeList = lmsService.getAllEmployeeData();
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data fetched Succesfully..!");
		response.setLmsList(Arrays.asList(employeeList));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}

	// URL for deleting employee by Admin

	@DeleteMapping("deleteEmployeeById/{id}")
	public ResponseEntity<LmsResponse> deleteEmployee(@PathVariable("id") int employeeId)
			throws EmployeeNotFoundException,BatchNotFoundException {
		EmployeePrimaryInfoDTO infoDTO = lmsService.deleteByID(employeeId);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data Deleted Succesfully..!");
		response.setLmsList(Arrays.asList(infoDTO));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}

	// adding mentor details by Admin

	@PostMapping("/addMentors")
	public ResponseEntity<LmsResponse> addMentors(@RequestBody MentorDetailsDTO mentorDetailsDTO)
			throws MessagingException, MentorNotFoundException {
		MentorDetailsDTO infoDTO = lmsService.addMentors(mentorDetailsDTO);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data added Succesfully..!");
		response.setLmsList(Arrays.asList(infoDTO));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}

	// URL for Setting Password By Mentor

	@PutMapping("/resetPasswordMentor")
	public ResponseEntity<LmsResponse> resetMentorPassword(@RequestBody MentorDetailsDTO mentorDetailsDTO)
			throws EmailNotFoundException {
		MentorDetailsDTO infoDTO = lmsService.resetMentorPassword(mentorDetailsDTO);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Password Changed");
		response.setLmsList(Arrays.asList(infoDTO));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.ACCEPTED);
	}
	// URL for getting all mentor details by Admin

	@GetMapping("/getMentors")
	public ResponseEntity<LmsResponse> getAllMentor() throws MentorNotFoundException {
		List<MentorDetailsDTO> mentor = lmsService.getAllMentorDetail();
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data fetched Succesfully..!");
		response.setLmsList(Arrays.asList(mentor));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}
	// URL for modifying mentor details by Admin

	@PutMapping("modifyMentorById/{id}")
	public ResponseEntity<LmsResponse> modifyMentorDetails(@PathVariable("id") int mentorNo,
			@RequestBody MentorDetailsDTO mentorDetailsDTO) throws MentorNotFoundException {
		MentorDetailsDTO infoDTO = lmsService.updateMentorDetails(mentorNo, mentorDetailsDTO);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data Modified Succesfully..!");
		response.setLmsList(Arrays.asList(infoDTO));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}

	// URL for deleting mentor by Admin by his Id
	@DeleteMapping("/deleteMentorById/{id}")
	public ResponseEntity<LmsResponse> deleteMentor(@PathVariable("id") int mentorId) throws MentorNotFoundException {
		MentorDetailsDTO infoDTO = lmsService.deleteMentorByID(mentorId);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data Deleted Succesfully..!");
		response.setLmsList(Arrays.asList(infoDTO));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}

	// Batch Operations

	// URL for adding Batch Details By Admin

	@PostMapping("/addbatch")
	public ResponseEntity<LmsResponse> addBatches(@RequestBody BatchDetailsDTO batchDetailsDTO)
			throws BatchNotFoundException,MessagingException, MentorNotFoundException, EmployeeNotFoundException {
		BatchDetailsDTO infoDTO = lmsService.addBatches(batchDetailsDTO);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data added Succesfully..!");
		response.setLmsList(Arrays.asList(infoDTO));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}

	// URL for fetching all Batch Details

	@GetMapping("/getBatches")
	public ResponseEntity<LmsResponse> getAllBatches() throws BatchNotFoundException {
		List<BatchDetailsDTO> batch = lmsService.getAllBatchDetail();
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data fetched Succesfully..!");
		response.setLmsList(Arrays.asList(batch));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}

	// URL for modifying Batch Details By Id through Admin

	@PutMapping("/admin/modifyBatch/{id}")
	public ResponseEntity<LmsResponse> modifyBatchDetails(@PathVariable("id") int batchNo,
			@RequestBody BatchDetailsDTO batchDetailsDTO) throws BatchNotFoundException {
		BatchDetailsDTO batch = lmsService.updateBatchDetail(batchNo, batchDetailsDTO);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data Modified Succesfully..!");
		response.setLmsList(Arrays.asList(batch));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}

	// URL for deleting Batch By Id Through Admin

	@DeleteMapping("/admin/deleteBatch/{id}")
	public ResponseEntity<LmsResponse> deleteBatch(@PathVariable("id") int batchId) throws BatchNotFoundException {
		BatchDetailsDTO infoDTO = lmsService.deleteBatchByID(batchId);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data Deleted Succesfully..!");
		response.setLmsList(Arrays.asList(infoDTO));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}

	// Mentor_Cntroller Details

	// URL to create mock by Mentor
	@PostMapping("/{mentorId}/createMock/{batchId}")
	public ResponseEntity<LmsResponse> addMockInfo(@RequestBody MockDetailsDTO mockDetailsDTO,
			@PathVariable("mentorId") int mentorId, @PathVariable("batchId") int batchId) throws MockNotFoundException {
		MockDetailsDTO infoDTO = lmsService.addMockInformation(mockDetailsDTO, mentorId, batchId);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data added Succesfully..!");
		response.setLmsList(Arrays.asList(infoDTO));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}

	//URL for giving attendance to employee
	@PutMapping("/getAttendance/{id}")
	public ResponseEntity<LmsResponse> giveAttendance(@PathVariable("id") int id) throws EmployeeNotFoundException {
		 EmployeePrimaryInfoDTO dto = lmsService.giveAttendance(id);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Attendance added Succesfully..!");
		response.setLmsList(Arrays.asList(dto));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}
	
	// URL for giving Mock Ratings
	@PostMapping("/giveRating/{id}")
	public ResponseEntity<LmsResponse> giveMockRatings(@RequestBody MockRatingDTO mockRatingDTO,
			@PathVariable("id") int id) throws MockNotFoundException,EmployeeNotFoundException {
		MockRatingDTO infoDTO = lmsService.giveRatings(mockRatingDTO, id);
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data added Succesfully..!");
		response.setLmsList(Arrays.asList(infoDTO));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}

	// URL for fetching All Approved Employee data

	@GetMapping("/getAllApprovedEmployeeData")
	public ResponseEntity<LmsResponse> getAllApprovedEmployeeData() throws EmployeeNotFoundException {
		List<EmployeeRequestedDTO> allApprovedEmployeeData = lmsService.getAllApprovedEmployeeData();
		response.setError(false);
		response.setStatus(200);
		response.setMessage("Data added Succesfully..!");
		response.setLmsList(Arrays.asList(allApprovedEmployeeData));
		return new ResponseEntity<LmsResponse>(response, HttpStatus.OK);
	}
}