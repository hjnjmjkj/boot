package com.hk.ssm4.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.ssm4.entity.UserEntity;
import com.hk.ssm4.enums.UserSexEnum;

@Service
public class UserService {
	@Autowired
	private com.hk.ssm4.mapper.master.UserMapper masterUser;
	@Autowired
	private com.hk.ssm4.mapper.salve.UserMapperSalve salveUser;
	@Transactional
	public void userInsert(){
		masterUser.insert(new UserEntity("hk","123",UserSexEnum.MAN));
		salveUser.insert(new UserEntity("hk","123",UserSexEnum.MAN));
	}
}
