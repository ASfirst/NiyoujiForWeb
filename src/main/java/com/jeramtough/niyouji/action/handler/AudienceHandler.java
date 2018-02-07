package com.jeramtough.niyouji.action.handler;

import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.action.AudienceCommandActions;
import com.jeramtough.niyouji.business.AudienceBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author 11718
 */
@Controller
public class AudienceHandler extends BaseWebSocketHandler
{
	
	private AudienceBusiness audienceBusiness;
	
	@Autowired
	public AudienceHandler(AudienceBusiness audienceBusiness)
	{
		this.audienceBusiness = audienceBusiness;
	}
	
	@Override
	public void handleSocketMessage(WebSocketSession session, SocketMessage socketMessage)
	{
		int action = socketMessage.getCommandAction();
		switch (action)
		{
			case AudienceCommandActions.ENTER_PERFORMING_ROOM:
				audienceBusiness.enterPerformingRoom(session, socketMessage);
				break;
			case AudienceCommandActions.SEND_AUDIENCE_BARRAGE:
				audienceBusiness.sendAudienceBarrage(session, socketMessage);
				break;
			case AudienceCommandActions.LIGHT_ATTENTION_COUNT:
				audienceBusiness.lightAttentionCount(session, socketMessage);
				break;
			case AudienceCommandActions.AUDIENCE_LEAVE:
				audienceBusiness.audienceLeave(session,socketMessage);
				break;
			default:
		}
	}
}
