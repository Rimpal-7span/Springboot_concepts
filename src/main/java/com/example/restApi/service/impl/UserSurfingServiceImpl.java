package com.example.restApi.service.impl;

import com.example.restApi.service.UserActionService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Service
@Profile("surfing")
public class UserSurfingServiceImpl implements UserActionService
{
	@Override
	public void userAction()
	{
		System.out.println("user can Surfing");
	}
}
