package com.jeramtough.niyouji.action.controller;

import com.jeramtough.jtlog3.P;
import com.jeramtough.niyouji.bean.travelnote.Appraise;
import com.jeramtough.niyouji.business.AppraiseBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 11718
 */
@Controller
public class AppraiseController
{
	private AppraiseBusiness appraiseBusiness;
	
	@Autowired
	public AppraiseController(AppraiseBusiness appraiseBusiness)
	{
		this.appraiseBusiness = appraiseBusiness;
	}
	
	@PostMapping("/publishAppraise")
	@ResponseBody
	public String publishAppraise(@RequestBody Appraise appraise)
	{
		appraiseBusiness.addAppraise(appraise);
		return "666";
	}
	
	@GetMapping("/getAppraisesCount")
	@ResponseBody
	public int getAppraisesCount(@RequestParam("travelnoteId") String travelnoteId)
	{
		return appraiseBusiness.getAppraisesCount(travelnoteId);
	}
}
