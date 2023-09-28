package com.te.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lms.entity.BatchDetails;
import com.te.lms.entity.EmployeePrimaryInfo;
@Repository
public interface LmsBatchRepository extends JpaRepository<BatchDetails, Integer> {
	public List<EmployeePrimaryInfo> findBybatchId(int id);
}
