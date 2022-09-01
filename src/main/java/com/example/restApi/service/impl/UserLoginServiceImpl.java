package com.example.restApi.service.impl;

import com.example.restApi.service.UserActionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("login")
public class UserLoginServiceImpl implements UserActionService
{

	@Override
	public void userAction()
	{
		System.out.println("user can Login");
	}
}
