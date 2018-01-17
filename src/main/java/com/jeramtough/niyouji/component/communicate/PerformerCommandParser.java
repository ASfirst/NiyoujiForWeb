package com.jeramtough.niyouji.component.communicate;

import com.alibaba.fastjson.JSON;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.CreatePerformingRoomCommand;

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
}
