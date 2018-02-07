package com.jeramtough.niyouji.action.controller;

import com.jeramtough.niyouji.bean.travelnote.FinishedTravelnoteCover;
import com.jeramtough.niyouji.bean.travelnote.LiveTravelnoteCover;
import com.jeramtough.niyouji.bean.travelnote.Travelnote;
import com.jeramtough.niyouji.business.TravelnoteBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 11718
 */
@Controller
public class TravelnoteController
{
	@Autowired
	private TravelnoteBusiness travelnoteBusiness;
	
	@RequestMapping("/getLiveTravelnoteCovers")
	@ResponseBody
	public LiveTravelnoteCover[] getLiveTravelnoteCovers()
	{
		return travelnoteBusiness.getLiveTravelnoteCovers();
	}
	
	@RequestMapping("/getFinishedTravelnoteCovers")
	@ResponseBody
	public List<FinishedTravelnoteCover> getFinishedTravelnoteCovers(
			@RequestParam(value = "fromTravelnoteId", required = false) String fromTravelnoteId)
	{
		return travelnoteBusiness.getFinishedTravelnoteCovers(fromTravelnoteId);
	}
	
	@RequestMapping("/getTravelnote")
	@ResponseBody
	public Travelnote getTravelnote(
			@RequestParam(value = "travelnoteId", required = true) String travelnoteId)
	{
		return travelnoteBusiness.getTravelnote(travelnoteId);
	}
}
