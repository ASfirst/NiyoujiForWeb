package com.jeramtough.niyouji.business;

import com.alibaba.fastjson.JSON;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.action.ServerCommandActions;
import com.jeramtough.niyouji.bean.socketmessage.command.audience.AudienceLeaveCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.audience.EnterPerformingRoomCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.audience.LightAttentionCountCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.audience.SendAudienceBarrageCommand;
import com.jeramtough.niyouji.bean.travelnote.Barrage;
import com.jeramtough.niyouji.bean.travelnote.Travelnote;
import com.jeramtough.niyouji.bean.travelnote.TravelnotePage;
import com.jeramtough.niyouji.component.communicate.factory.AudienceSocketMessageFactory;
import com.jeramtough.niyouji.component.communicate.parser.AudienceCommandParser;
import com.jeramtough.niyouji.component.performing.PerformingRoom;
import com.jeramtough.niyouji.component.performing.PerformingRoomsManager;
import com.jeramtough.niyouji.util.SocketSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

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
		
		SocketMessage socketMessage1;
		
		//如果房间以失效
		if (performingRoom == null)
		{
			socketMessage1 =
					new SocketMessage(ServerCommandActions.DONT_EXIST_LIVE_TRAVELNOTE);
			SocketSessionUtil.sendSocketMessage(session, socketMessage1);
			
			return;
		}
		
		//将Travelnote实体返回给客户端初始化界面
		Travelnote travelnote = performingRoom.getTravelnote();
		socketMessage1 = new SocketMessage(ServerCommandActions.RETURN_LIVE_TRAVELNOTE);
		socketMessage1.setCommand(JSON.toJSONString(travelnote));
		SocketSessionUtil.sendSocketMessage(session, socketMessage1);
		
		//将进入直播间的行为广播到各个，除了进入直播间的用户本身
		broadcastActionToPerformerAndAudiences(performingRoom.getPerformerSession(),
				performingRoom.getAudienceSessions(), socketMessage);
		
		//最后进入直播间
		performingRoom.enterAudience(session);
	}
	
	@Override
	public void sendAudienceBarrage(WebSocketSession session, SocketMessage socketMessage)
	{
		SendAudienceBarrageCommand sendAudienceBarrageCommand =
				AudienceCommandParser.parseSendAudienceBarrageCommand(socketMessage);
		
		PerformingRoom performingRoom = performingRoomsManager
				.getPerformingRoom(sendAudienceBarrageCommand.getPerformerId());
		
		//添加一个弹幕实体
		Travelnote travelnote = performingRoom.getTravelnote();
		TravelnotePage travelnotePage =
				travelnote.getTravelnotePage(sendAudienceBarrageCommand.getPosition());
		Barrage barrage = new Barrage();
		barrage.setContent(sendAudienceBarrageCommand.getContent());
		barrage.setCreateTime(sendAudienceBarrageCommand.getCreateTime());
		barrage.setIsPerformers(sendAudienceBarrageCommand.isPerformers());
		barrage.setNickname(sendAudienceBarrageCommand.getNickname());
		travelnotePage.addBarrage(barrage);
		
		broadcastActionToPerformerAndAudiences(performingRoom.getPerformerSession(),
				performingRoom.getAudienceSessions(), socketMessage);
	}
	
	@Override
	public void lightAttentionCount(WebSocketSession session, SocketMessage socketMessage)
	{
		LightAttentionCountCommand lightAttentionCountCommand =
				AudienceCommandParser.parseLightAttentionCountCommand(socketMessage);
		
		PerformingRoom performingRoom = performingRoomsManager
				.getPerformingRoom(lightAttentionCountCommand.getPerformerId());
		
		//点亮数加一
		Travelnote travelnote = performingRoom.getTravelnote();
		travelnote.setAttentionsCount(travelnote.getAttentionsCount() + 1);
		
		broadcastActionToPerformerAndAudiences(performingRoom.getPerformerSession(),
				performingRoom.getAudienceSessions(), socketMessage);
	}
	
	
	@Override
	public void audienceLeave(WebSocketSession audienceSession, SocketMessage socketMessage)
	{
		AudienceLeaveCommand audienceLeaveCommand =
				AudienceCommandParser.parseAudienceLeaveCommand(socketMessage);
		
		PerformingRoom performingRoom = performingRoomsManager
				.getPerformingRoom(audienceLeaveCommand.getPerformerId());
		
		performingRoom.removeAudienceSession(audienceSession);
		
		//广播
		broadcastActionToPerformerAndAudiences(performingRoom.getPerformerSession(),
				performingRoom.getAudienceSessions(), socketMessage);
	}
	
	//************************************
	private void broadcastActionToPerformerAndAudiences(WebSocketSession performerSession,
			List<WebSocketSession> audienceSessions, SocketMessage socketMessage)
	{
		//将观众行为广播给主播
		SocketSessionUtil.sendSocketMessage(performerSession, socketMessage);
		
		//将观众行为广播给所有观众
		SocketSessionUtil.sendSocketMessage(audienceSessions, socketMessage);
	}
}
