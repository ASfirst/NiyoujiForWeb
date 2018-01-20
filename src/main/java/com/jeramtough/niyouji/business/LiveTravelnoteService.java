package com.jeramtough.niyouji.business;

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
	public Travelnote[] getLiveTravelnotes()
	{
		int size=8;
		List<PerformingRoom> performingRooms=performingRoomsManager.getAllPerformingRooms();
		if (performingRooms.size()<size)
		{
			size=performingRooms.size();
		}
		
		Travelnote[] travelnotes=new Travelnote[size];
		
		for (int i=0;i<size;i++)
		{
			travelnotes[i]=performingRooms.get(i).getTravelnote();
		}
		
		return travelnotes;
	}
}
