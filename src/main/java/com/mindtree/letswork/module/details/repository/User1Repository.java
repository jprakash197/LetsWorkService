package com.mindtree.letswork.module.details.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.letswork.module.details.entity.User;

@Repository
public interface User1Repository extends JpaRepository<User, Integer> {

}