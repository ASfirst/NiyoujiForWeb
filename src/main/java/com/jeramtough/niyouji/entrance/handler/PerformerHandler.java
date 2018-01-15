package com.jeramtough.niyouji.entrance.handler;

import com.jeramtough.jtlog3.P;
import com.jeramtough.niyouji.bean.socket.SocketMessage;
import com.jeramtough.niyouji.bean.socket.command.CommandActions;
import com.jeramtough.niyouji.bean.socket.command.CommandFactory;
import com.jeramtough.niyouji.bean.socket.command.CreatePerformingRoomCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author 11718
 */
@Controller
public class PerformerHandler extends BaseWebSocketHandler
{
	
	@Override
	public void handleSocketMessage(WebSocketSession session, SocketMessage socketMessage)
	{
		switch (socketMessage.getCommandAction())
		{
			case CommandActions.CREATE_PERFORMING_ROOM:
				
				CreatePerformingRoomCommand createPerformingRoomCommand= CommandFactory
						.toCreatePerformingRoomCommand(socketMessage.getCommand());
				P.debug(createPerformingRoomCommand);
				
				break;
			default:
		}
	}
}
