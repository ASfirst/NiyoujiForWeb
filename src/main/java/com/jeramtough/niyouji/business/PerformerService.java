package com.jeramtough.niyouji.business;

import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.action.ServerCommandActions;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.AddPageCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.CreatePerformingRoomCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.DeletePageCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.SelectPageCommand;
import com.jeramtough.niyouji.bean.travelnote.Travelnote;
import com.jeramtough.niyouji.bean.travelnote.TravelnotePage;
import com.jeramtough.niyouji.component.performing.PerformingRoom;
import com.jeramtough.niyouji.component.performing.PerformingRoomsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class PerformerService implements PerformerBusiness
{
	
	private PerformingRoomsManager performingRoomsManager;
	
	@Autowired
	public PerformerService(PerformingRoomsManager performingRoomsManager)
	{
		this.performingRoomsManager = performingRoomsManager;
	}
	
	@Override
	public SocketMessage createPerformingRoom(WebSocketSession webSocketSession,
			CreatePerformingRoomCommand createPerformingRoomCommand)
	{
		Travelnote travelnote = new Travelnote();
		travelnote.setTravelnoteTitle(createPerformingRoomCommand.getTravelnoteTitle());
		travelnote.setCreateTime(createPerformingRoomCommand.getCreateTime());
		travelnote.setCoverType(createPerformingRoomCommand.getCoverType());
		travelnote.setCoverResourceUrl(createPerformingRoomCommand.getCoverResourceUrl());
		
		PerformingRoom performingRoom =
				new PerformingRoom(createPerformingRoomCommand.getPerformerId(), travelnote,
						webSocketSession);
		performingRoomsManager.addPerformingRoom(createPerformingRoomCommand.getPerformerId(),
				performingRoom);
		
		SocketMessage socketMessage =
				new SocketMessage(ServerCommandActions.CREATING_PERFORMING_ROOM_FINISH);
		return socketMessage;
	}
	
	@Override
	public synchronized void travelnoteAddPage(AddPageCommand addPageCommand)
	{
		PerformingRoom performingRoom =
				performingRoomsManager.getPerformingRoom(addPageCommand.getPerformerId());
		
		Travelnote travelnote = performingRoom.getTravelnote();
		
		TravelnotePage travelnotePage = new TravelnotePage();
		travelnotePage.setCreateTime(addPageCommand.getCreateTime());
		travelnotePage.setPageType(addPageCommand.getPageType());
		travelnotePage.setThemePosition(addPageCommand.getThemePosition());
		
		travelnote.addTravelnotePage(travelnotePage);
		
		//广播主播行为到客户端上
	}
	
	@Override
	public synchronized void travelnoteSelectPage(SelectPageCommand selectPageCommand)
	{
	
	}
	
	@Override
	public synchronized void travelnoteDeletePage(DeletePageCommand deletePageCommand)
	{
		PerformingRoom performingRoom =
				performingRoomsManager.getPerformingRoom(deletePageCommand.getPerformerId());
		
		Travelnote travelnote = performingRoom.getTravelnote();
		
		travelnote.deleteTravelnotePage(deletePageCommand.getPosition());
	}
}
