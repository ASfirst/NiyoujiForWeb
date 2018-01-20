package com.jeramtough.niyouji.component.performing;

import com.jeramtough.jtlog3.WithLogger;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.travelnote.Travelnote;
import com.jeramtough.niyouji.util.SocketSessionUtil;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;

/**
 * @author 11718
 */
public class PerformingRoom implements WithLogger
{
	private Travelnote travelnote;
	private ArrayList<WebSocketSession> audienceSessions;
	private WebSocketSession performerSession;
	
	public PerformingRoom(Travelnote travelnote, WebSocketSession performerSession)
	{
		this.travelnote = travelnote;
		this.performerSession = performerSession;
		
		audienceSessions = new ArrayList<>();
		
		getP().info(
				"The performer" + travelnote.getPerformerId() + " create a performing room " +
						"" + this.hashCode());
	}
	
	public Travelnote getTravelnote()
	{
		return travelnote;
	}
	
	public WebSocketSession getPerformerSession()
	{
		return performerSession;
	}
	
	public void enterAudience(WebSocketSession session)
	{
		audienceSessions.add(session);
		getP().info("A audience enter the performing room " + hashCode());
	}
	
	public ArrayList<WebSocketSession> getAudienceSessions()
	{
		return audienceSessions;
	}
	
}
