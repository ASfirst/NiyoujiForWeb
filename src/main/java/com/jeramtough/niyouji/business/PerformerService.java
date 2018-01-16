package com.jeramtough.niyouji.business;

import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.command.client.CreatePerformingRoomCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.server.ServerCommandActions;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class PerformerService implements PerformerBusiness
{
	@Override
	public SocketMessage createPerformingRoom(WebSocketSession webSocketSession,
			CreatePerformingRoomCommand createPerformingRoomCommand)
	{
		SocketMessage socketMessage =
				new SocketMessage(ServerCommandActions.CREATING_PERFORMING_ROOM_FINISH);
		return socketMessage;
	}
}
