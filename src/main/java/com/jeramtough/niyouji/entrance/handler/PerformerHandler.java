package com.jeramtough.niyouji.entrance.handler;

import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.action.PerformerCommandActions;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.AddPageCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.CreatePerformingRoomCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.DeletePageCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.SelectPageCommand;
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
			
			case PerformerCommandActions.DELETEED_PAGE:
				DeletePageCommand deletePageCommand =
						PerformerCommandParser.parseDeletePageCommand(socketMessage);
				performerBusiness.travelnoteDeletePage(deletePageCommand);
				break;
			
			case PerformerCommandActions.PAGE_SET_IMAGE:
				break;
			case PerformerCommandActions.PAGE_SET_VIDEO:
				break;
			case PerformerCommandActions.PAGE_TEXT_CHANGED:
				break;
			case PerformerCommandActions.PAGE_SET_THEME:
				break;
			case PerformerCommandActions.PAGE_SET_BACKGROUND_MUSIC:
				break;
			case PerformerCommandActions.SENT_PERFORMER_BARRAGE:
				break;
			case PerformerCommandActions.TRAVELNOTE_END:
				break;
			
			default:
		}
	}
}
