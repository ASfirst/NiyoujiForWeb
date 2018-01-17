package com.jeramtough.niyouji.business;

import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.action.ServerCommandActions;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.CreatePerformingRoomCommand;
import com.jeramtough.niyouji.component.performing.PerformingRoom;
import com.jeramtough.niyouji.component.performing.PerformingRoomsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class PerformerService implements PerformerBusiness
{
	
	private PerformingRoomsManager performingRoomsManager;
	
	@Autowired
	public PerformerService(PerformingRoomsManager performingRoomsManager)
	{
		this.performingRoomsManager = performingRoomsManager;
	}
	
	@Override
	public SocketMessage createPerformingRoom(WebSocketSession webSocketSession,
			CreatePerformingRoomCommand createPerformingRoomCommand)
	{
		PerformingRoom performingRoom =
				new PerformingRoom(webSocketSession, createPerformingRoomCommand);
		performingRoomsManager
				.addPerformingRoom(createPerformingRoomCommand.getOwnerId(), performingRoom);
		
		SocketMessage socketMessage =
				new SocketMessage(ServerCommandActions.CREATING_PERFORMING_ROOM_FINISH);
		return socketMessage;
	}
}
