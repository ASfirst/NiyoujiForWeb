package com.jeramtough.niyouji.business;

import com.jeramtough.jtutil.StringUtil;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.action.ServerCommandActions;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.*;
import com.jeramtough.niyouji.bean.travelnote.Barrage;
import com.jeramtough.niyouji.bean.travelnote.Travelnote;
import com.jeramtough.niyouji.bean.travelnote.TravelnotePage;
import com.jeramtough.niyouji.component.communicate.parser.PerformerCommandParser;
import com.jeramtough.niyouji.component.performing.PerformingRoom;
import com.jeramtough.niyouji.component.performing.PerformingRoomsManager;
import com.jeramtough.niyouji.util.SocketSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class PerformerService implements PerformerBusiness
{
	private PerformingRoomsManager performingRoomsManager;
	private ExecutorService executorService;
	
	@Autowired
	public PerformerService(PerformingRoomsManager performingRoomsManager)
	{
		this.performingRoomsManager = performingRoomsManager;
		
		executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>());
	}
	
	
	@Override
	public void createPerformingRoom(WebSocketSession webSocketSession,
			SocketMessage socketMessage)
	{
		CreatePerformingRoomCommand createPerformingRoomCommand =
				PerformerCommandParser.parseCreatePerformingRoomCommand(socketMessage);
		
		Travelnote travelnote = new Travelnote();
		travelnote.setPerformerId(createPerformingRoomCommand.getPerformerId());
		travelnote.setTravelnoteTitle(createPerformingRoomCommand.getTravelnoteTitle());
		travelnote.setCreateTime(createPerformingRoomCommand.getCreateTime());
		travelnote.setCoverType(createPerformingRoomCommand.getCoverType());
		travelnote.setCoverResourceUrl(createPerformingRoomCommand.getCoverResourceUrl());
		
		PerformingRoom performingRoom = new PerformingRoom(travelnote, webSocketSession);
		performingRoomsManager.addPerformingRoom(createPerformingRoomCommand.getPerformerId(),
				performingRoom);
		
		//会送以创建房间完成命令给主播
		SocketMessage socketMessage1 =
				new SocketMessage(ServerCommandActions.CREATING_PERFORMING_ROOM_FINISH);
		SocketSessionUtil.sendSocketMessage(webSocketSession, socketMessage1);
	}
	
	@Override
	public void travelnoteAddPage(SocketMessage socketMessage)
	{
		AddPageCommand addPageCommand =
				PerformerCommandParser.parseAddPageCommand(socketMessage);
		
		PerformingRoom performingRoom =
				performingRoomsManager.getPerformingRoom(addPageCommand.getPerformerId());
		
		Travelnote travelnote = performingRoom.getTravelnote();
		
		TravelnotePage travelnotePage = new TravelnotePage();
		travelnotePage.setTravelnoteId(travelnote.getTravelnoteId());
		travelnotePage.setCreateTime(addPageCommand.getCreateTime());
		travelnotePage.setPageType(addPageCommand.getPageType());
		
		travelnote.addTravelnotePage(travelnotePage);
		
		//广播主播行为到客户端上
		broadcastPerformerCommandToAndiences(performingRoom, socketMessage);
	}
	
	@Override
	public void travelnoteSelectPage(SocketMessage socketMessage)
	{
		SelectPageCommand selectPageCommand =
				PerformerCommandParser.parseSelectPageCommand(socketMessage);
		
		PerformingRoom performingRoom =
				performingRoomsManager.getPerformingRoom(selectPageCommand.getPerformerId());
		
		//广播主播行为到客户端上
		broadcastPerformerCommandToAndiences(performingRoom, socketMessage);
	}
	
	@Override
	public void travelnoteDeletePage(SocketMessage socketMessage)
	{
		DeletePageCommand deletePageCommand =
				PerformerCommandParser.parseDeletePageCommand(socketMessage);
		
		PerformingRoom performingRoom =
				performingRoomsManager.getPerformingRoom(deletePageCommand.getPerformerId());
		
		Travelnote travelnote = performingRoom.getTravelnote();
		
		travelnote.deleteTravelnotePage(deletePageCommand.getPosition());
		
		//广播主播行为到客户端上
		broadcastPerformerCommandToAndiences(performingRoom, socketMessage);
	}
	
	@Override
	public void travelnotePageSetImage(SocketMessage socketMessage)
	{
		PageSetImageCommand pageSetImageCommand =
				PerformerCommandParser.parsePageSetImageCommand(socketMessage);
		
		PerformingRoom performingRoom =
				performingRoomsManager.getPerformingRoom(pageSetImageCommand.getPerformerId());
		Travelnote travelnote = performingRoom.getTravelnote();
		TravelnotePage travelnotePage =
				travelnote.getTravelnotePage(pageSetImageCommand.getPosition());
		travelnotePage.setResourceUrl(pageSetImageCommand.getImageUrl());
		
		//广播主播行为到客户端上
		broadcastPerformerCommandToAndiences(performingRoom, socketMessage);
	}
	
	@Override
	public void travelnotePageSetVideo(SocketMessage socketMessage)
	{
		PageSetVideoCommand pageSetVideoCommand =
				PerformerCommandParser.parsePageSetVideoCommand(socketMessage);
		
		PerformingRoom performingRoom =
				performingRoomsManager.getPerformingRoom(pageSetVideoCommand.getPerformerId());
		Travelnote travelnote = performingRoom.getTravelnote();
		TravelnotePage travelnotePage =
				travelnote.getTravelnotePage(pageSetVideoCommand.getPosition());
		travelnotePage.setResourceUrl(pageSetVideoCommand.getVideoUrl());
		
		//广播主播行为到客户端上
		broadcastPerformerCommandToAndiences(performingRoom, socketMessage);
	}
	
	@Override
	public void travelnotePageTextChange(SocketMessage socketMessage)
	{
		PageTextChangeCommand pageTextChangeCommand =
				PerformerCommandParser.parsePageTextChangeCommand(socketMessage);
		
		PerformingRoom performingRoom = performingRoomsManager
				.getPerformingRoom(pageTextChangeCommand.getPerformerId());
		
		Travelnote travelnote = performingRoom.getTravelnote();
		TravelnotePage travelnotePage =
				travelnote.getTravelnotePage(pageTextChangeCommand.getPosition());
		
		String textContent = travelnotePage.getTextContent();
		textContent = StringUtil.addOrDeleteWords(textContent, pageTextChangeCommand.isAdded(),
				pageTextChangeCommand.getStart(), pageTextChangeCommand.getWords());
		travelnotePage.setTextContent(textContent);
		
		//广播主播行为到客户端上
		broadcastPerformerCommandToAndiences(performingRoom, socketMessage);
	}
	
	@Override
	public void travelnotePageSetTheme(SocketMessage socketMessage)
	{
		PageSetThemeCommand pageSetThemeCommand =
				PerformerCommandParser.parsePageSetThemeCommand(socketMessage);
		
		PerformingRoom performingRoom =
				performingRoomsManager.getPerformingRoom(pageSetThemeCommand.getPerformerId());
		Travelnote travelnote = performingRoom.getTravelnote();
		TravelnotePage travelnotePage =
				travelnote.getTravelnotePage(pageSetThemeCommand.getPosition());
		
		travelnotePage.setThemePosition(pageSetThemeCommand.getThemePosition());
		
		//广播主播行为到客户端上
		broadcastPerformerCommandToAndiences(performingRoom, socketMessage);
	}
	
	@Override
	public void travelnotePageSetBackgroundMusic(SocketMessage socketMessage)
	{
		PageSetBackgroundMusicCommand pageSetBackgroundMusicCommand =
				PerformerCommandParser.parsePageSetBackgroundMusicCommand(socketMessage);
		
		PerformingRoom performingRoom = performingRoomsManager
				.getPerformingRoom(pageSetBackgroundMusicCommand.getPerformerId());
		
		Travelnote travelnote = performingRoom.getTravelnote();
		TravelnotePage travelnotePage =
				travelnote.getTravelnotePage(pageSetBackgroundMusicCommand.getPosition());
		travelnotePage.setBackgroundMusicPath(pageSetBackgroundMusicCommand.getMusicPath());
		
		//广播主播行为到客户端上
		broadcastPerformerCommandToAndiences(performingRoom, socketMessage);
	}
	
	@Override
	public void sentPerformerBarrage(SocketMessage socketMessage)
	{
		SendPerformerBarrageCommand sendPerformerBarrageCommand =
				PerformerCommandParser.parseSendPerformerBarrageCommand(socketMessage);
		
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
		
		//广播主播行为到客户端上
		broadcastPerformerCommandToAndiences(performingRoom, socketMessage);
	}
	
	@Override
	public void travelnoteEnd(SocketMessage socketMessage)
	{
		TravelnoteEndCommand travelnoteEndCommand =
				PerformerCommandParser.parseTravelnoteEndCommand(socketMessage);
		
		PerformingRoom performingRoom = performingRoomsManager
				.getPerformingRoom(travelnoteEndCommand.getPerformerId());
		
		try
		{
			performingRoom.getPerformerSession().close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		Travelnote travelnote = performingRoom.getTravelnote();
		ArrayList<TravelnotePage> travelnotePages = travelnote.getTravelnotePages();
		
		for (TravelnotePage travelnotePage : travelnotePages)
		{
			//			P.debug(travelnotePage.toString());
		}
		
		//广播主播行为到客户端上
		broadcastPerformerCommandToAndiences(performingRoom, socketMessage);
	}
	
	
	//*******************************
	private void broadcastPerformerCommandToAndiences(PerformingRoom performingRoom,
			SocketMessage socketMessage)
	{
		for (int i = 0; i < performingRoom.getAudienceSessions().size(); i++)
		{
			int finalI = i;
			executorService.submit(() ->
			{
				WebSocketSession audienceSession =
						performingRoom.getAudienceSessions().get(finalI);
				if (audienceSession.isOpen())
				{
					SocketSessionUtil.sendSocketMessage(audienceSession, socketMessage);
				}
			});
		}
	}
}
