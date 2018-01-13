package com.jeramtough.niyouji.entrance.controller;

import com.jeramtough.jtlog3.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 11718
 */
@Controller
public class TestController
{
	@RequestMapping("/hello")
	@ResponseBody
	public String test()
	{
		P.arrive();
		return "hello";
	}
}
