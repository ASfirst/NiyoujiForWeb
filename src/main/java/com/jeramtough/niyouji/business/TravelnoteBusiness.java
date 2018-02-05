package com.jeramtough.niyouji.business;

import com.jeramtough.niyouji.bean.travelnote.FinishedTravelnoteCover;
import com.jeramtough.niyouji.bean.travelnote.LiveTravelnoteCover;

import java.util.List;

/**
 * @author 11718
 */
public interface TravelnoteBusiness
{
	LiveTravelnoteCover[] getLiveTravelnoteCovers();
	
	List<FinishedTravelnoteCover> getFinishedTravelnoteCovers(String fromTravelnoteId);
}
