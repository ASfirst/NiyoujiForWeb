package com.jeramtough.niyouji.action.controller;

import com.jeramtough.jtlog3.P;
import com.jeramtough.niyouji.bean.user.NiyoujiUser;
import com.jeramtough.niyouji.business.UserBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 11718
 */
@Controller
public class UserController
{
	private UserBusiness userBusiness;
	
	@Autowired
	public UserController(UserBusiness userBusiness)
	{
		this.userBusiness = userBusiness;
	}
	
	@RequestMapping("/getNiyoujiUser")
	@ResponseBody
	public NiyoujiUser getNiyoujiUser(
			@RequestParam(value = "userId", required = true) String userId)
	{
		return userBusiness.getNiyoujiUser(userId);
	}
}
