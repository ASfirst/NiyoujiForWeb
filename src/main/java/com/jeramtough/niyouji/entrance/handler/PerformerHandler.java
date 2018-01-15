package com.jeramtough.niyouji.entrance.handler;

import com.jeramtough.jtlog3.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author 11718
 */
@Controller
public class PerformerHandler extends TextWebSocketHandler
{
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception
	{
		super.afterConnectionEstablished(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message)
			throws Exception
	{
		System.out.println(message.getPayload());
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
			throws Exception
	{
		super.afterConnectionClosed(session, status);
		P.arrive();
	}
}
