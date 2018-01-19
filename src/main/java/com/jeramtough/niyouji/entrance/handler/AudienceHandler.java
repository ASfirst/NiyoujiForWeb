package com.jeramtough.niyouji.entrance.handler;

import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author 11718
 */
@Controller
public class AudienceHandler extends BaseWebSocketHandler
{
	@Override
	public void handleSocketMessage(WebSocketSession session, SocketMessage socketMessage)
	{
	
	}
}
