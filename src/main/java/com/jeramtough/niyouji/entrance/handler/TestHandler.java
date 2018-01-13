package com.jeramtough.niyouji.entrance.handler;

import com.jeramtough.jtlog3.P;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class TestHandler extends TextWebSocketHandler
{
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message)
			throws Exception
	{
		System.out.println(message.getPayload());
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception
	{
		super.afterConnectionEstablished(session);
		P.arrive();
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
			throws Exception
	{
		super.afterConnectionClosed(session, status);
		P.arrive();
	}
}
