package com.jeramtough.niyouji.business;

import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.command.client.CreatePerformingRoomCommand;
import org.springframework.web.socket.WebSocketSession;

public interface PerformerBusiness
{
	SocketMessage createPerformingRoom(WebSocketSession webSocketSession,CreatePerformingRoomCommand
			createPerformingRoomCommand);
}
