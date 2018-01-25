package com.jeramtough.niyouji.business;

import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.*;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author 11718
 */
public interface PerformerBusiness
{
	void createPerformingRoom(WebSocketSession webSocketSession, SocketMessage socketMessage);
	
	void travelnoteAddPage(SocketMessage socketMessage);
	
	void travelnoteSelectPage(SocketMessage socketMessage);
	
	void travelnoteDeletePage(SocketMessage socketMessage);
	
	void travelnotePageSetImage(SocketMessage socketMessage);
	
	void travelnotePageSetVideo(SocketMessage socketMessage);
	
	void travelnotePageTextChange(SocketMessage socketMessage);
	
	void travelnotePageSetTheme(SocketMessage socketMessage);
	
	void travelnotePageSetBackgroundMusic(SocketMessage socketMessage);
	
	void sentPerformerBarrage(SocketMessage socketMessage);
	
	void performerLeave(WebSocketSession session);
	
	void performerReback(WebSocketSession session, SocketMessage socketMessage);
	
	void travelnoteEnd(SocketMessage socketMessage);
	
	
}
