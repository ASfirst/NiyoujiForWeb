package com.jeramtough.niyouji.entrance.handler;

import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.action.PerformerCommandActions;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.CreatePerformingRoomCommand;
import com.jeramtough.niyouji.business.PerformerBusiness;
import com.jeramtough.niyouji.component.communicate.PerformerCommandParser;
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
			case PerformerCommandActions.CREATE_PERFORMING_ROOM:
				CreatePerformingRoomCommand createPerformingRoomCommand =
						PerformerCommandParser.parseCreatePerformingRoomCommand(socketMessage);
				
				//创建房间并且返回客户端创建完成消息
				SocketMessage socketMessage1 = performerBusiness
						.createPerformingRoom(session, createPerformingRoomCommand);
				sendSocketMessage(session, socketMessage1);
				break;
			
			default:
		}
	}
}
