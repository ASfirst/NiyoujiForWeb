package com.jeramtough.niyouji.component.performing;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<PerformingRoom> getAllPerformingRooms()
	{
		ArrayList<PerformingRoom> performingRoomArrayList=new ArrayList<>();
		
		for (Map.Entry<String, PerformingRoom> performingRoomEntry:performingRooms.entrySet())
		{
			performingRoomArrayList.add(performingRoomEntry.getValue());
		}
		
		return performingRoomArrayList;
	}
}
