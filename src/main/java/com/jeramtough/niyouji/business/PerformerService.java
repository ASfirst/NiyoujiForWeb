package com.jeramtough.niyouji.business;

import com.jeramtough.jtlog3.P;
import com.jeramtough.jtutil.StringUtil;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.action.ServerCommandActions;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.*;
import com.jeramtough.niyouji.bean.travelnote.Appraise;
import com.jeramtough.niyouji.bean.travelnote.Barrage;
import com.jeramtough.niyouji.bean.travelnote.Travelnote;
import com.jeramtough.niyouji.bean.travelnote.TravelnotePage;
import com.jeramtough.niyouji.component.communicate.factory.PerformerSocketMessageFactory;
import com.jeramtough.niyouji.component.communicate.parser.PerformerCommandParser;
import com.jeramtough.niyouji.component.performing.PerformingRoom;
import com.jeramtough.niyouji.component.performing.PerformingRoomsManager;
import com.jeramtough.niyouji.dao.mapper.AppraiseMapper;
import com.jeramtough.niyouji.dao.mapper.BarrageMapper;
import com.jeramtough.niyouji.dao.mapper.TravelnoteMapper;
import com.jeramtough.niyouji.dao.mapper.TravelnotePageMapper;
import com.jeramtough.niyouji.util.SocketSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author 11718
 */
@Service
public class PerformerService implements PerformerBusiness
{
	private PerformingRoomsManager performingRoomsManager;
	
	private TravelnotePageMapper travelnotePageMapper;
	private TravelnoteMapper travelnoteMapper;
	private BarrageMapper barrageMapper;
	private AppraiseMapper appraiseMapper;
	
	@Autowired
	public PerformerService(PerformingRoomsManager performingRoomsManager,
			TravelnotePageMapper travelnotePageMapper, TravelnoteMapper travelnoteMapper,
			BarrageMapper barrageMapper, AppraiseMapper appraiseMapper)
	{
		this.performingRoomsManager = performingRoomsManager;
		this.travelnotePageMapper = travelnotePageMapper;
		this.travelnoteMapper = travelnoteMapper;
		this.barrageMapper = barrageMapper;
		this.appraiseMapper = appraiseMapper;
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
		travelnote.setLocation(createPerformingRoomCommand.getLocation());
		
		PerformingRoom performingRoom = new PerformingRoom(travelnote, webSocketSession);
		performingRoomsManager.addPerformingRoom(createPerformingRoomCommand.getPerformerId(),
				webSocketSession, performingRoom);
		
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
		
		//广播主播行为到观众端上
		SocketSessionUtil
				.sendSocketMessage(performingRoom.getAudienceSessions(), socketMessage);
	}
	
	@Override
	public void travelnoteSelectPage(SocketMessage socketMessage)
	{
		SelectPageCommand selectPageCommand =
				PerformerCommandParser.parseSelectPageCommand(socketMessage);
		
		PerformingRoom performingRoom =
				performingRoomsManager.getPerformingRoom(selectPageCommand.getPerformerId());
		
		//广播主播行为到观众端上
		SocketSessionUtil
				.sendSocketMessage(performingRoom.getAudienceSessions(), socketMessage);
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
		
		//广播主播行为到观众端上
		SocketSessionUtil
				.sendSocketMessage(performingRoom.getAudienceSessions(), socketMessage);
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
		
		//广播主播行为到观众端上
		SocketSessionUtil
				.sendSocketMessage(performingRoom.getAudienceSessions(), socketMessage);
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
		
		//广播主播行为到观众端上
		SocketSessionUtil
				.sendSocketMessage(performingRoom.getAudienceSessions(), socketMessage);
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
		
		//广播主播行为到观众端上
		SocketSessionUtil
				.sendSocketMessage(performingRoom.getAudienceSessions(), socketMessage);
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
		
		//广播主播行为到观众端上
		SocketSessionUtil
				.sendSocketMessage(performingRoom.getAudienceSessions(), socketMessage);
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
		
		//广播主播行为到观众端上
		SocketSessionUtil
				.sendSocketMessage(performingRoom.getAudienceSessions(), socketMessage);
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
		
		//广播主播行为到观众端上
		SocketSessionUtil
				.sendSocketMessage(performingRoom.getAudienceSessions(), socketMessage);
	}
	
	@Override
	public void performerLeave(WebSocketSession session)
	{
		PerformingRoom performingRoom = performingRoomsManager.getPerformingRoom(session);
		if (performingRoom != null)
		{
			PerformerLeaveCommand performerLeaveCommand = new PerformerLeaveCommand();
			SocketMessage socketMessage = PerformerSocketMessageFactory
					.processPerformerLeaveCommandSocketMessage(performerLeaveCommand);
			
			//延迟10秒发送主播断开连接事件，为了和主播主动结束游记区分
			try
			{
				Thread.sleep(10 * 1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			SocketSessionUtil
					.sendSocketMessage(performingRoom.getAudienceSessions(), socketMessage);
		}
	}
	
	
	@Override
	public void performerReback(WebSocketSession session, SocketMessage socketMessage)
	{
		PerformerRebackCommand performerRebackCommand =
				PerformerCommandParser.parsePerformerRebackCommand(socketMessage);
		
		PerformingRoom performingRoom = performingRoomsManager
				.getPerformingRoom(performerRebackCommand.getPerformerId());
		
		performingRoom.setPerformerSession(session);
		
		performingRoomsManager.updatePerformingRoom(session, performingRoom);
		
		//当前观众数和双击数
		Travelnote travelnote = performingRoom.getTravelnote();
		performerRebackCommand.setAttentionsCount(travelnote.getAttentionsCount());
		performerRebackCommand.setAudiencesCount(performingRoom.getAudiencesCount());
		socketMessage = PerformerSocketMessageFactory
				.processPerformerRebackCommandSocketMessage(performerRebackCommand);
		
		//将主播重连广播到观众端和主播端上
		SocketSessionUtil.sendSocketMessage(session, socketMessage);
		SocketSessionUtil
				.sendSocketMessage(performingRoom.getAudienceSessions(), socketMessage);
	}
	
	@Override
	public void travelnoteEnd(SocketMessage socketMessage)
	{
		TravelnoteEndCommand travelnoteEndCommand =
				PerformerCommandParser.parseTravelnoteEndCommand(socketMessage);
		
		PerformingRoom performingRoom = performingRoomsManager
				.getPerformingRoom(travelnoteEndCommand.getPerformerId());
		
		//广播主播行为到观众端上
		SocketSessionUtil
				.sendSocketMessage(performingRoom.getAudienceSessions(), socketMessage);
		
		//关闭主播sessiion
		try
		{
			performingRoom.getPerformerSession().close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		performingRoomsManager.removePerformingRoom(performingRoom);
		
		//游记写入持久层
		synchronized (this)
		{
			Travelnote travelnote = performingRoom.getTravelnote();
			if (travelnote.getTravelnotePages().size() > 0)
			{
				travelnoteMapper.insertTravelnote(travelnote);
				int travelnoteId = travelnoteMapper.getLastTravelnoteId();
				
				ArrayList<TravelnotePage> travelnotePages = travelnote.getTravelnotePages();
				for (TravelnotePage travelnotePage : travelnotePages)
				{
					travelnotePage.setTravelnoteId(travelnoteId + "");
					travelnotePageMapper.insertTravelnotePage(travelnotePage);
					int pageId = travelnotePageMapper.getLastPageId();
					
					for (Barrage barrage : travelnotePage.getBarrages())
					{
						barrage.setTravelnoteId(travelnoteId + "");
						barrage.setPageId(pageId + "");
						
						barrageMapper.insertBarrage(barrage);
						
						Appraise appraise = new Appraise();
						appraise.setTravelnoteId(travelnoteId + "");
						appraise.setContent(barrage.getContent());
						appraise.setCreateTime(barrage.getCreateTime());
						appraise.setNickname(barrage.getNickname());
						
						appraiseMapper.insertAppraise(appraise);
					}
				}
			}
		}
		
		
	}
	
	
}
