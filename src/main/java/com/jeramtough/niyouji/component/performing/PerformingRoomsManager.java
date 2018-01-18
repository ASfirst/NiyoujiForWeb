package com.jeramtough.niyouji.component.performing;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author 11718
 */
@Component
public class PerformingRoomsManager
{
	private HashMap<String, PerformingRoom> performingRooms;
	
	public PerformingRoomsManager()
	{
		performingRooms = new HashMap<>();
	}
	
	public void addPerformingRoom(String performerId, PerformingRoom performingRoom)
	{
		performingRooms.put(performerId, performingRoom);
	}
	
	public PerformingRoom getPerformingRoom(String performerId)
	{
		return performingRooms.get(performerId);
	}
}
