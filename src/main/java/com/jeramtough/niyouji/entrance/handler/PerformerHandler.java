package com.jeramtough.niyouji.entrance.handler;

import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.action.PerformerCommandActions;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.*;
import com.jeramtough.niyouji.business.PerformerBusiness;
import com.jeramtough.niyouji.component.communicate.PerformerCommandParser;
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
		switch (socketMessage.getCommandAction())
		{
			case PerformerCommandActions.CREATE_PERFORMING_ROOM:
				CreatePerformingRoomCommand createPerformingRoomCommand =
						PerformerCommandParser.parseCreatePerformingRoomCommand(socketMessage);
				
				//创建房间并且返回客户端创建完成消息
				SocketMessage socketMessage1 = performerBusiness
						.createPerformingRoom(session, createPerformingRoomCommand);
				sendSocketMessage(session, socketMessage1);
				break;
			
			case PerformerCommandActions.ADDED_PAGE:
				AddPageCommand addPageCommand =
						PerformerCommandParser.parseAddPageCommand(socketMessage);
				performerBusiness.travelnoteAddPage(addPageCommand);
				break;
			
			case PerformerCommandActions.SELECTED_PAGE:
				SelectPageCommand selectPageCommand =
						PerformerCommandParser.parseSelectPageCommand(socketMessage);
				performerBusiness.travelnoteSelectPage(selectPageCommand);
				break;
			
			case PerformerCommandActions.DELETED_PAGE:
				DeletePageCommand deletePageCommand =
						PerformerCommandParser.parseDeletePageCommand(socketMessage);
				performerBusiness.travelnoteDeletePage(deletePageCommand);
				break;
			
			case PerformerCommandActions.PAGE_SET_IMAGE:
				PageSetImageCommand pageSetImageCommand =
						PerformerCommandParser.parsePageSetImageCommand(socketMessage);
				performerBusiness.travelnotePageSetImage(pageSetImageCommand);
				break;
			
			case PerformerCommandActions.PAGE_SET_VIDEO:
				PageSetVideoCommand pageSetVideoCommand =
						PerformerCommandParser.parsePageSetVideoCommand(socketMessage);
				performerBusiness.travelnotePageSetVideo(pageSetVideoCommand);
				break;
			
			case PerformerCommandActions.PAGE_SET_THEME:
				PageSetThemeCommand pageSetThemeCommand =
						PerformerCommandParser.parsePageSetThemeCommand(socketMessage);
				performerBusiness.travelnotePageSetTheme(pageSetThemeCommand);
				break;
			
			case PerformerCommandActions.PAGE_SET_BACKGROUND_MUSIC:
				PageSetBackgroundMusicCommand pageSetBackgroundMusicCommand =
						PerformerCommandParser
								.parsePageSetBackgroundMusicCommand(socketMessage);
				performerBusiness
						.travelnotePageSetBackgroundMusic(pageSetBackgroundMusicCommand);
				break;
			
			case PerformerCommandActions.PAGE_TEXT_CHANGED:
				PageTextChangeCommand pageTextChangeCommand =
						PerformerCommandParser.parsePageTextChangeCommand(socketMessage);
				performerBusiness.travelnotePageTextChange(pageTextChangeCommand);
				break;
			
			case PerformerCommandActions.SENT_PERFORMER_BARRAGE:
				SendPerformerBarrageCommand sendPerformerBarrageCommand =
						PerformerCommandParser.parseSendPerformerBarrageCommand(socketMessage);
				performerBusiness.sentPerformerBarrage(sendPerformerBarrageCommand);
				break;
			
			case PerformerCommandActions.TRAVELNOTE_END:
				TravelnoteEndCommand travelnoteEndCommand =
						PerformerCommandParser.parseTravelnoteEndCommand(socketMessage);
				performerBusiness.travelnoteEnd(travelnoteEndCommand);
				break;
			
			default:
		}
	}
}
