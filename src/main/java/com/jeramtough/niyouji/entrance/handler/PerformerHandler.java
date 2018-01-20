package com.jeramtough.niyouji.entrance.handler;

import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.action.PerformerCommandActions;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.*;
import com.jeramtough.niyouji.business.PerformerBusiness;
import com.jeramtough.niyouji.component.communicate.parser.PerformerCommandParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author 11718
 */
@Controller
public class PerformerHandler extends BaseWebSocketHandler
{
	private PerformerBusiness performerBusiness;
	
	@Autowired
	public PerformerHandler(PerformerBusiness performerBusiness)
	{
		this.performerBusiness = performerBusiness;
	}
	
	@Override
	public void handleSocketMessage(WebSocketSession session, SocketMessage socketMessage)
	{
		int action = socketMessage.getCommandAction();
		switch (action)
		{
			case PerformerCommandActions.CREATE_PERFORMING_ROOM:
				performerBusiness.createPerformingRoom(session, socketMessage);
				break;
			
			case PerformerCommandActions.ADDED_PAGE:
				performerBusiness.travelnoteAddPage(socketMessage);
				break;
			
			case PerformerCommandActions.SELECTED_PAGE:
				performerBusiness.travelnoteSelectPage(socketMessage);
				break;
			
			case PerformerCommandActions.DELETED_PAGE:
				performerBusiness.travelnoteDeletePage(socketMessage);
				break;
			
			case PerformerCommandActions.PAGE_SET_IMAGE:
				performerBusiness.travelnotePageSetImage(socketMessage);
				break;
			
			case PerformerCommandActions.PAGE_SET_VIDEO:
				performerBusiness.travelnotePageSetVideo(socketMessage);
				break;
			
			case PerformerCommandActions.PAGE_SET_THEME:
				performerBusiness.travelnotePageSetTheme(socketMessage);
				break;
			
			case PerformerCommandActions.PAGE_SET_BACKGROUND_MUSIC:
				performerBusiness.travelnotePageSetBackgroundMusic(socketMessage);
				break;
			
			case PerformerCommandActions.PAGE_TEXT_CHANGED:
				performerBusiness.travelnotePageTextChange(socketMessage);
				break;
			
			case PerformerCommandActions.SENT_PERFORMER_BARRAGE:
				performerBusiness.sentPerformerBarrage(socketMessage);
				break;
			
			case PerformerCommandActions.TRAVELNOTE_END:
				performerBusiness.travelnoteEnd(socketMessage);
				break;
			
			default:
		}
	}
}
