package com.jeramtough.niyouji.business;

import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.*;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author 11718
 */
public interface PerformerBusiness
{
	SocketMessage createPerformingRoom(WebSocketSession webSocketSession,
			CreatePerformingRoomCommand createPerformingRoomCommand);
	
	void travelnoteAddPage(AddPageCommand addPageCommand);
	
	void travelnoteSelectPage(SelectPageCommand selectPageCommand);
	
	void travelnoteDeletePage(DeletePageCommand deletePageCommand);
	
	void travelnotePageSetImage(PageSetImageCommand pageSetImageCommand);
	
	void travelnotePageSetVideo(PageSetVideoCommand pageSetVideoCommand);
	
	void travelnotePageTextChange(PageTextChangeCommand pageTextChangeCommand);
	
	void travelnotePageSetTheme(PageSetThemeCommand pageSetThemeCommand);
	
	void travelntePageSetBackgroundMusic(PageSetBackgroundMusicCommand
			pageSetBackgroundMusicCommand);
	
	void sentPerformerBarrage(SendPerformerBarrageCommand sendPerformerBarrageCommand);
	
	void travelnoteEnd(TravelnoteEndCommand travelnoteEndCommand);
}
