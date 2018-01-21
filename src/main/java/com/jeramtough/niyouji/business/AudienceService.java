package com.jeramtough.niyouji.business;

import com.alibaba.fastjson.JSON;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.action.ServerCommandActions;
import com.jeramtough.niyouji.bean.socketmessage.command.audience.EnterPerformingRoomCommand;
import com.jeramtough.niyouji.bean.travelnote.Travelnote;
import com.jeramtough.niyouji.component.communicate.parser.AudienceCommandParser;
import com.jeramtough.niyouji.component.performing.PerformingRoom;
import com.jeramtough.niyouji.component.performing.PerformingRoomsManager;
import com.jeramtough.niyouji.util.SocketSessionUtil;
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
	public void enterPerformingRoom(WebSocketSession session, SocketMessage socketMessage)
	{
		EnterPerformingRoomCommand enterPerformingRoomCommand =
				AudienceCommandParser.parseEnterPerformingRoomCommand(socketMessage);
		
		PerformingRoom performingRoom = performingRoomsManager
				.getPerformingRoom(enterPerformingRoomCommand.getPerformerId());
		
		//讲Travelnote实体返回给客户端初始化界面
		Travelnote travelnote = performingRoom.enterAudience(session);
		SocketMessage socketMessage1 =
				new SocketMessage(ServerCommandActions.RETURN_LIVE_TRAVELNOTE);
		socketMessage1.setCommand(JSON.toJSONString(travelnote));
		SocketSessionUtil.sendSocketMessage(session, socketMessage1);
		
		//将观众行为广播给主播
		
		//将观众行为广播给所有观众
	}
}
