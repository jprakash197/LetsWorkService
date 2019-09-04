package com.mindtree.letswork.module.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.letswork.module.authentication.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
