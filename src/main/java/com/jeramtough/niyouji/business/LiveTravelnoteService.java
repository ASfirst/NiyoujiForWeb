package com.jeramtough.niyouji.business;

import com.jeramtough.niyouji.bean.travelnote.LiveTravelnoteCover;
import com.jeramtough.niyouji.bean.travelnote.Travelnote;
import com.jeramtough.niyouji.component.performing.PerformingRoom;
import com.jeramtough.niyouji.component.performing.PerformingRoomsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 11718
 */
@Service
public class LiveTravelnoteService implements LiveTravelnoteBusiness
{
	private PerformingRoomsManager performingRoomsManager;
	
	@Autowired
	public LiveTravelnoteService(PerformingRoomsManager performingRoomsManager)
	{
		this.performingRoomsManager = performingRoomsManager;
	}
	
	@Override
	public LiveTravelnoteCover[] getLiveTravelnoteCovers()
	{
		int size = 4;
		List<PerformingRoom> performingRooms = performingRoomsManager.getAllPerformingRooms();
		if (performingRooms.size() < size)
		{
			size = performingRooms.size();
		}
		
		LiveTravelnoteCover[] liveTravelnoteCovers = new LiveTravelnoteCover[size];
		for (int i = 0; i < size; i++)
		{
			Travelnote travelnote = performingRooms.get(i).getTravelnote();
			LiveTravelnoteCover liveTravelnoteCover = new LiveTravelnoteCover();
			
			liveTravelnoteCover.setAttentionsCount(travelnote.getAttentionsCount());
			liveTravelnoteCover.setCoverResourceUrl(travelnote.getCoverResourceUrl());
			liveTravelnoteCover.setCoverType(travelnote.getCoverType());
			liveTravelnoteCover.setCreateTime(travelnote.getCreateTime());
			liveTravelnoteCover.setPerformerId(travelnote.getPerformerId());
			liveTravelnoteCover.setAudiencesCount(performingRooms.get(i).getAudiencesCount());
			
			//这部分业务代码未完成哦
			liveTravelnoteCover.setPerformerNickname("Nickname");
			liveTravelnoteCover.setTravelnoteTitle(travelnote.getTravelnoteTitle());
			
			liveTravelnoteCovers[i] = liveTravelnoteCover;
		}
		
		return liveTravelnoteCovers;
	}
}
