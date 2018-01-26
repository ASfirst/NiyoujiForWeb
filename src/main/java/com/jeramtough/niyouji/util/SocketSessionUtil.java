package com.jeramtough.niyouji.util;

import com.alibaba.fastjson.JSON;
import com.jeramtough.jtlog3.P;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SocketSessionUtil
{
	public static void sendSocketMessage(List<WebSocketSession> sessions,
			SocketMessage socketMessage)
	{
		ExecutorService executorService =
				new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
						new SynchronousQueue<Runnable>());
		
		for (int i = 0; i < sessions.size(); i++)
		{
			int finalI = i;
			executorService.submit(() ->
			{
				WebSocketSession session = sessions.get(finalI);
				sendSocketMessage(session, socketMessage);
			});
		}
		
		executorService.shutdown();
	}
	
	public static void sendSocketMessage(WebSocketSession session, SocketMessage socketMessage)
	{
		if (session.isOpen())
		{
			String jsonMessage = JSON.toJSONString(socketMessage);
			try
			{
				session.sendMessage(new TextMessage(jsonMessage));
				
				P.info("WebSocketMessage","sent a message["+jsonMessage+"] to the "+session
						.getId()
						+" client");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
