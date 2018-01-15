package com.jeramtough.niyouji.component.performing;

import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;

public class PerformingRoom
{
	private String travelnoteId;
	private ArrayList<WebSocketSession> audienceSessions;
	private WebSocketSession performerSession;
	
}
