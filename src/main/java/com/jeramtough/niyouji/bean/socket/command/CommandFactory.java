package com.jeramtough.niyouji.bean.socket.command;

import com.alibaba.fastjson.JSON;
import com.jeramtough.niyouji.bean.socket.SocketMessage;

import java.util.zip.CRC32;

public class CommandFactory
{
	public static String toJsonCommand(Object object)
	{
		return JSON.toJSONString(object, false);
	}
	
	public static SocketMessage processCreatePerformingRoomSocketMessage(
			CreatePerformingRoomCommand createPerformingRoomCommand)
	{
		SocketMessage socketMessage = new SocketMessage();
		socketMessage.setCommandAction(CommandActions.CREATE_PERFORMING_ROOM);
		socketMessage.setCommand(JSON.toJSONString(createPerformingRoomCommand));
		return socketMessage;
	}
	
	public static CreatePerformingRoomCommand toCreatePerformingRoomCommand(String jsonStr)
	{
		return JSON.parseObject(jsonStr, CreatePerformingRoomCommand.class);
	}
}
