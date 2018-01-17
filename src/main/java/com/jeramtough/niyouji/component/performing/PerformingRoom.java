package com.jeramtough.niyouji.component.performing;

import com.jeramtough.niyouji.bean.socketmessage.command.performer.CreatePerformingRoomCommand;
import com.jeramtough.niyouji.bean.travelnote.Travelnote;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;

/**
 * @author 11718
 */
public class PerformingRoom
{
	private Travelnote travelnote;
	private ArrayList<WebSocketSession> audienceSessions;
	private WebSocketSession performerSession;
	
	
	
	public PerformingRoom(WebSocketSession performerSession,
			CreatePerformingRoomCommand createPerformingRoomCommand)
	{
		this.performerSession=performerSession;
		
		travelnote=new Travelnote();
		travelnote.setCoverResourceUrl(createPerformingRoomCommand.getCoverResourceUrl());
		travelnote.setCoverType(createPerformingRoomCommand.getCoverType());
		travelnote.setCreateTime(createPerformingRoomCommand.getCreateTime());
		travelnote.setOwnerId(createPerformingRoomCommand.getOwnerId());
		travelnote.setTravelnoteTitle(createPerformingRoomCommand.getTravelnoteTitle());
		travelnote.setTravelnoteId(createPerformingRoomCommand.getTravelnoteId());
	}
	
	public Travelnote getTravelnote()
	{
		return travelnote;
	}
	
	public WebSocketSession getPerformerSession()
	{
		return performerSession;
	}
}
