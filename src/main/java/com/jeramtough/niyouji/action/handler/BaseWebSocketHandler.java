package com.jeramtough.niyouji.action.handler;

import com.alibaba.fastjson.JSON;
import com.jeramtough.jtlog3.P;
import com.jeramtough.jtlog3.WithLogger;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * @author 11718
 */
public abstract class BaseWebSocketHandler extends TextWebSocketHandler implements WithLogger
{
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception
	{
		super.afterConnectionEstablished(session);
		getP().info("connect with " + session.getId() + " session");
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message)
			throws Exception
	{
		getP().info("handle a new message [" + message.getPayload() + "] from client");
		handleSocketMessage(session,
				JSON.parseObject(message.getPayload(), SocketMessage.class));
	}
	
	public abstract void handleSocketMessage(WebSocketSession session,
			SocketMessage socketMessage);
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
			throws Exception
	{
		super.afterConnectionClosed(session, status);
		getP().error("close " + session.getId() + " session because " + status.getReason());
	}
	
	public void sendSocketMessage(WebSocketSession session, SocketMessage socketMessage)
	{
		String jsonMessage = JSON.toJSONString(socketMessage);
		try
		{
			session.sendMessage(new TextMessage(jsonMessage));
			
			getP().info("sent a message[" + jsonMessage + "] to clients");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message)
	{
		try
		{
			getP().info("pong");
			session.sendMessage(message);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	protected void handlePongMessage(WebSocketSession session, PongMessage message)
			throws Exception
	{
		super.handlePongMessage(session, message);
		P.arrive();
	}
}
