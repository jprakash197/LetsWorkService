package com.mindtree.letswork.module.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.letswork.module.authentication.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	@Query("from User where username=?1")
	User findUserByUserName(String username);

	@Query("from User where token=?1")
	Optional<User> findByToken(String token);
	
	@Query("from User where referral_code=?1")
	Optional<User> findByStringID(String referralCode);
	
	@Transactional
	@Modifying
	@Query("UPDATE User SET token=?1 WHERE referral_code =?2")
	void updateToken(String token, String referralCode);
}
