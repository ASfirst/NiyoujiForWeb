package com.jeramtough.niyouji.component.communicate;

import com.alibaba.fastjson.JSON;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.AddPageCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.CreatePerformingRoomCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.DeletePageCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.SelectPageCommand;

/**
 * @author 11718
 * on 2018  January 16 Tuesday 19:04.
 */

public class PerformerCommandParser
{
	public static CreatePerformingRoomCommand parseCreatePerformingRoomCommand(
			SocketMessage socketMessage)
	{
		return JSON.parseObject(socketMessage.getCommand(), CreatePerformingRoomCommand.class);
	}
	
	public static SelectPageCommand parseSelectPageCommand(SocketMessage socketMessage)
	{
		return JSON.parseObject(socketMessage.getCommand(), SelectPageCommand.class);
	}
	
	public static AddPageCommand parseAddPageCommand(SocketMessage socketMessage)
	{
		return JSON.parseObject(socketMessage.getCommand(), AddPageCommand.class);
	}
	
	public static DeletePageCommand parseDeletePageCommand(SocketMessage socketMessage)
	{
		return JSON.parseObject(socketMessage.getCommand(), DeletePageCommand.class);
	}
}
