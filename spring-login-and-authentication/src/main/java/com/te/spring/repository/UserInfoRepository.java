package com.te.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.spring.model.UserInfo;
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	UserInfo findByuserName(String userName);

}
