package com.te.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lms.entity.MentorDetails;
@Repository
public interface LmsMentorRepository extends JpaRepository<MentorDetails, Integer> {
 public MentorDetails findBymentorEmail(String email);
}
