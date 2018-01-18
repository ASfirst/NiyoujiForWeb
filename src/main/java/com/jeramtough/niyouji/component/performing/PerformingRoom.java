package com.jeramtough.niyouji.component.performing;

import com.jeramtough.niyouji.bean.travelnote.Travelnote;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;

/**
 * @author 11718
 */
public class PerformingRoom
{
	private String performerId;
	private Travelnote travelnote;
	private ArrayList<WebSocketSession> audienceSessions;
	private WebSocketSession performerSession;
	
	public PerformingRoom(String performerId, Travelnote travelnote, WebSocketSession performerSession)
	{
		this.performerId = performerId;
		this.travelnote = travelnote;
		this.performerSession = performerSession;
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
