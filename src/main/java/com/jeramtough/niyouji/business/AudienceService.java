package com.jeramtough.niyouji.business;

import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.command.audience.EnterPerformingRoomCommand;
import com.jeramtough.niyouji.component.communicate.parser.AudienceCommandParser;
import com.jeramtough.niyouji.component.performing.PerformingRoom;
import com.jeramtough.niyouji.component.performing.PerformingRoomsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author 11718
 */
@Service
public class AudienceService implements AudienceBusiness
{
	private PerformingRoomsManager performingRoomsManager;
	
	@Autowired
	public AudienceService(PerformingRoomsManager performingRoomsManager)
	{
		this.performingRoomsManager = performingRoomsManager;
	}
	
	@Override
	public void enterPerformingRoom(WebSocketSession session,
			SocketMessage socketMessage)
	{
		EnterPerformingRoomCommand enterPerformingRoomCommand =
				AudienceCommandParser.parseEnterPerformingRoomCommand(socketMessage);
		
		PerformingRoom performingRoom = performingRoomsManager
				.getPerformingRoom(enterPerformingRoomCommand.getPerformerId());
		
		performingRoom.enterAudience(session);
	}
}
