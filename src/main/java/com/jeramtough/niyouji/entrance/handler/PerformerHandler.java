package com.jeramtough.niyouji.entrance.handler;

import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.command.client.ClientCommandActions;
import com.jeramtough.niyouji.bean.socketmessage.command.client.ClientCommandParser;
import com.jeramtough.niyouji.bean.socketmessage.command.client.CreatePerformingRoomCommand;
import com.jeramtough.niyouji.business.PerformerBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author 11718
 */
@Controller
public class PerformerHandler extends BaseWebSocketHandler
{
	private PerformerBusiness performerBusiness;
	
	@Autowired
	public PerformerHandler(PerformerBusiness performerBusiness)
	{
		this.performerBusiness = performerBusiness;
	}
	
	@Override
	public void handleSocketMessage(WebSocketSession session, SocketMessage socketMessage)
	{
		switch (socketMessage.getCommandAction())
		{
			case ClientCommandActions.CREATE_PERFORMING_ROOM:
				
				CreatePerformingRoomCommand createPerformingRoomCommand =
						ClientCommandParser.parseCreatePerformingRoomCommand(socketMessage);
				SocketMessage socketMessage1 = performerBusiness
						.createPerformingRoom(session, createPerformingRoomCommand);
				sendSocketMessage(session, socketMessage1);
				break;
			default:
		}
	}
}
