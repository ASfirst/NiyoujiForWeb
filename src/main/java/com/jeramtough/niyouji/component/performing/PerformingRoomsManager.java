package com.jeramtough.niyouji.component.performing;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

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
	private HashMap<WebSocketSession, PerformingRoom> performingRooms1;
	
	public PerformingRoomsManager()
	{
		performingRooms = new HashMap<>();
		performingRooms1 = new HashMap<>();
	}
	
	public void addPerformingRoom(String performerId, WebSocketSession performerSession,
			PerformingRoom performingRoom)
	{
		performingRooms.put(performerId, performingRoom);
		performingRooms1.put(performerSession, performingRoom);
	}
	
	public PerformingRoom getPerformingRoom(String performerId)
	{
		return performingRooms.get(performerId);
	}
	
	public PerformingRoom getPerformingRoom(WebSocketSession session)
	{
		return performingRooms1.get(session);
	}
	
	public List<PerformingRoom> getAllPerformingRooms()
	{
		ArrayList<PerformingRoom> performingRoomArrayList = new ArrayList<>();
		
		for (Map.Entry<String, PerformingRoom> performingRoomEntry : performingRooms
				.entrySet())
		{
			if (performingRoomEntry.getValue().getPerformerSession().isOpen())
			{
				performingRoomArrayList.add(performingRoomEntry.getValue());
			}
			else
			{
				performingRooms.remove(performingRoomEntry.getKey());
			}
		}
		
		return performingRoomArrayList;
	}
	
	public void updatePerformingRoom(WebSocketSession session, PerformingRoom performingRoom)
	{
		this.removePerformingRoom(performingRoom);
		
		performingRooms.put(performingRoom.getTravelnote().getPerformerId(), performingRoom);
		performingRooms1.put(session, performingRoom);
	}
	
	public void removePerformingRoom(PerformingRoom performingRoom)
	{
		performingRooms.remove(performingRoom);
		performingRooms1.remove(performingRoom);
	}
}
