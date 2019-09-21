package com.mindtree.letswork.module.details.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.letswork.module.details.entity.User;
import com.mindtree.letswork.module.details.repository.User1Repository;
import com.mindtree.letswork.module.details.service.UserService;

@Service

public class UserServiceImpl implements UserService {

	@Autowired
	private User1Repository trackRepository;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return trackRepository.findAll();
	}
	}
