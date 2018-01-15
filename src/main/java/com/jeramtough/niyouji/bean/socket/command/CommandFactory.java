package com.jeramtough.niyouji.bean.socket.command;

import com.alibaba.fastjson.JSON;
import com.jeramtough.niyouji.bean.socket.SocketMessage;

public class CommandFactory
{
	public static String toJsonCommand(Object object)
	{
		return JSON.toJSONString(object, false);
	}
	
	public static SocketMessage processCreatePerformingRoomSocketMessage
			(CreatePerformingRoomCommand createPerformingRoomCommand)
	{
		
		return null;
	}
}
