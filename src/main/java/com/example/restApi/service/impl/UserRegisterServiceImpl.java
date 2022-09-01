package com.example.restApi.service.impl;

import com.example.restApi.service.UserActionService;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("register")
@Primary
public class UserRegisterServiceImpl implements UserActionService
{
	@Override
	public void userAction()
	{
		System.out.println("user can register");
	}
}
