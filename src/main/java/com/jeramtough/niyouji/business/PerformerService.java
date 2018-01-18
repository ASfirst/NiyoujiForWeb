package com.jeramtough.niyouji.business;

import com.jeramtough.jtlog3.P;
import com.jeramtough.jtutil.StringUtil;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.action.ServerCommandActions;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.*;
import com.jeramtough.niyouji.bean.travelnote.Barrage;
import com.jeramtough.niyouji.bean.travelnote.Travelnote;
import com.jeramtough.niyouji.bean.travelnote.TravelnotePage;
import com.jeramtough.niyouji.component.performing.PerformingRoom;
import com.jeramtough.niyouji.component.performing.PerformingRoomsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;

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
	
	@Override
	public synchronized void travelnotePageSetImage(PageSetImageCommand pageSetImageCommand)
	{
		PerformingRoom performingRoom =
				performingRoomsManager.getPerformingRoom(pageSetImageCommand.getPerformerId());
		Travelnote travelnote = performingRoom.getTravelnote();
		TravelnotePage travelnotePage =
				travelnote.getTravelnotePage(pageSetImageCommand.getPosition());
		travelnotePage.setResourceUrl(pageSetImageCommand.getImageUrl());
	}
	
	@Override
	public synchronized void travelnotePageSetVideo(PageSetVideoCommand pageSetVideoCommand)
	{
		PerformingRoom performingRoom =
				performingRoomsManager.getPerformingRoom(pageSetVideoCommand.getPerformerId());
		Travelnote travelnote = performingRoom.getTravelnote();
		TravelnotePage travelnotePage =
				travelnote.getTravelnotePage(pageSetVideoCommand.getPosition());
		travelnotePage.setResourceUrl(pageSetVideoCommand.getVideoUrl());
	}
	
	@Override
	public synchronized void travelnotePageTextChange(
			PageTextChangeCommand pageTextChangeCommand)
	{
		PerformingRoom performingRoom = performingRoomsManager
				.getPerformingRoom(pageTextChangeCommand.getPerformerId());
		
		Travelnote travelnote = performingRoom.getTravelnote();
		TravelnotePage travelnotePage =
				travelnote.getTravelnotePage(pageTextChangeCommand.getPosition());
		
		String textContent = travelnotePage.getTextContent();
		textContent = StringUtil.addOrDeleteWords(textContent, pageTextChangeCommand.isAdded(),
				pageTextChangeCommand.getStart(), pageTextChangeCommand.getWords());
		travelnotePage.setTextContent(textContent);
	}
	
	@Override
	public synchronized void travelnotePageSetTheme(PageSetThemeCommand pageSetThemeCommand)
	{
		PerformingRoom performingRoom =
				performingRoomsManager.getPerformingRoom(pageSetThemeCommand.getPerformerId());
		Travelnote travelnote = performingRoom.getTravelnote();
		TravelnotePage travelnotePage =
				travelnote.getTravelnotePage(pageSetThemeCommand.getPosition());
		
		travelnotePage.setThemePosition(pageSetThemeCommand.getThemePosition());
	}
	
	@Override
	public synchronized void travelnotePageSetBackgroundMusic(
			PageSetBackgroundMusicCommand pageSetBackgroundMusicCommand)
	{
		PerformingRoom performingRoom = performingRoomsManager
				.getPerformingRoom(pageSetBackgroundMusicCommand.getPerformerId());
		
		Travelnote travelnote = performingRoom.getTravelnote();
		TravelnotePage travelnotePage =
				travelnote.getTravelnotePage(pageSetBackgroundMusicCommand.getPosition());
		travelnotePage.setBackgroundMusicPath(pageSetBackgroundMusicCommand.getMusicPath());
	}
	
	@Override
	public synchronized void sentPerformerBarrage(
			SendPerformerBarrageCommand sendPerformerBarrageCommand)
	{
		PerformingRoom performingRoom = performingRoomsManager
				.getPerformingRoom(sendPerformerBarrageCommand.getPerformerId());
		
		Travelnote travelnote = performingRoom.getTravelnote();
		TravelnotePage travelnotePage =
				travelnote.getTravelnotePage(sendPerformerBarrageCommand.getPosition());
		
		Barrage barrage = new Barrage();
		barrage.setContent(sendPerformerBarrageCommand.getContent());
		barrage.setCreateTime(sendPerformerBarrageCommand.getCreateTime());
		barrage.setIsPerformers(sendPerformerBarrageCommand.getIsPerformers());
		barrage.setNickname(sendPerformerBarrageCommand.getNickname());
		
		travelnotePage.addBarrage(barrage);
	}
	
	@Override
	public synchronized void travelnoteEnd(TravelnoteEndCommand travelnoteEndCommand)
	{
		PerformingRoom performingRoom = performingRoomsManager
				.getPerformingRoom(travelnoteEndCommand.getPerformerId());
		
		Travelnote travelnote = performingRoom.getTravelnote();
		ArrayList<TravelnotePage> travelnotePages = travelnote.getTravelnotePages();
		
		for (TravelnotePage travelnotePage : travelnotePages)
		{
			P.debug(travelnotePage.toString());
		}
	}
}
