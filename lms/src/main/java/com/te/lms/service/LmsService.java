package com.te.lms.service;

import java.util.List;

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

import jakarta.mail.MessagingException;

public interface LmsService {

	EmployeePrimaryInfoDTO sendEmployeeInfo(EmployeePrimaryInfoDTO primaryInfoDTO) throws  EmployeeNotFoundException;

	EmployeePrimaryInfoDTO approveById(int employeeNo, EmployeePrimaryInfoDTO primaryInfoDTO) throws EmployeeNotFoundException,MessagingException;

	MailRejectionDTO rejectEmployee(int id, MailRejectionDTO rejectionDTO) throws MessagingException,EmployeeNotFoundException;
	
	EmployeePrimaryInfoDTO resetPassword(EmployeePrimaryInfoDTO primaryInfoDTO)throws EmailNotFoundException;

	List<EmployeeRequestedDTO> getAllRequestedEmployeeData()throws EmployeeNotFoundException;
	
	EmployeePrimaryInfoDTO getEmployeeByUserId(int id) throws EmployeeNotFoundException;

	List<EmployeePrimaryInfoDTO> getAllEmployeeData()throws EmployeeNotFoundException ;
	
	EmployeePrimaryInfoDTO deleteByID(int employeeId)throws EmployeeNotFoundException,BatchNotFoundException;
	
	MentorDetailsDTO addMentors(MentorDetailsDTO mentorDetailsDTO) throws MessagingException,MentorNotFoundException;
	
	MentorDetailsDTO resetMentorPassword(MentorDetailsDTO mentorDetailsDTO)throws EmailNotFoundException;

	List<MentorDetailsDTO> getAllMentorDetail()throws MentorNotFoundException ;
	
	MentorDetailsDTO updateMentorDetails(int mentorNo, MentorDetailsDTO mentorDetailsDTO)throws MentorNotFoundException;
	
	MentorDetailsDTO deleteMentorByID(int mentorId)throws MentorNotFoundException;
	
	BatchDetailsDTO addBatches(BatchDetailsDTO batchDetailsDTO)throws BatchNotFoundException,MessagingException, MentorNotFoundException, EmployeeNotFoundException;
		
	List<BatchDetailsDTO> getAllBatchDetail()throws BatchNotFoundException;

	BatchDetailsDTO updateBatchDetail(int batchNo, BatchDetailsDTO batchDetailsDTO)throws BatchNotFoundException;

	BatchDetailsDTO deleteBatchByID(int batchId)throws BatchNotFoundException;

	MockDetailsDTO addMockInformation(MockDetailsDTO mockDetailsDTO,int mentorId,int batchId)throws MockNotFoundException;
	
	EmployeePrimaryInfoDTO giveAttendance(int id)throws EmployeeNotFoundException;
	
	MockRatingDTO giveRatings(MockRatingDTO mockRatingDTO,int id)throws MockNotFoundException,EmployeeNotFoundException;

	List<EmployeeRequestedDTO> getAllApprovedEmployeeData()throws  EmployeeNotFoundException;

}
