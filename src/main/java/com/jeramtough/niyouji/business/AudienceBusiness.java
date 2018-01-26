package com.jeramtough.niyouji.business;

import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author 11718
 */
public interface AudienceBusiness
{
	void enterPerformingRoom(WebSocketSession session, SocketMessage socketMessage);
	
	void sendAudienceBarrage(WebSocketSession session, SocketMessage socketMessage);
	
	void lightAttentionCount(WebSocketSession session, SocketMessage socketMessage);
	
	void audienceLeave(WebSocketSession performerId,SocketMessage audienceSession);
}
