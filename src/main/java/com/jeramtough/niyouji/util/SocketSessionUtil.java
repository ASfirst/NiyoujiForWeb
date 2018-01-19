package com.jeramtough.niyouji.util;

import com.alibaba.fastjson.JSON;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;

public class SocketSessionUtil
{
	public static void sendSocketMessage(ArrayList<WebSocketSession> sessions,
			SocketMessage socketMessage)
	{
		for (int i = 0; i < sessions.size(); i++)
		{
			WebSocketSession session = sessions.get(i);
			sendSocketMessage(session, socketMessage);
		}
	}
	
	public static void sendSocketMessage(WebSocketSession session, SocketMessage socketMessage)
	{
		String jsonMessage = JSON.toJSONString(socketMessage);
		try
		{
			session.sendMessage(new TextMessage(jsonMessage));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
