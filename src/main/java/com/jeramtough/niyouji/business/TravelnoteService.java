package com.jeramtough.niyouji.business;

import com.jeramtough.niyouji.bean.travelnote.*;
import com.jeramtough.niyouji.component.performing.PerformingRoom;
import com.jeramtough.niyouji.component.performing.PerformingRoomsManager;
import com.jeramtough.niyouji.dao.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 11718
 */
@Service
public class TravelnoteService implements TravelnoteBusiness
{
	private PerformingRoomsManager performingRoomsManager;
	private NiyoujiUserMapper niyoujiUserMapper;
	private TravelnoteMapper travelnoteMapper;
	private TravelnotePageMapper travelnotePageMapper;
	private AppraiseMapper appraiseMapper;
	private BarrageMapper barrageMapper;
	
	@Autowired
	public TravelnoteService(PerformingRoomsManager performingRoomsManager,
			NiyoujiUserMapper niyoujiUserMapper, TravelnoteMapper travelnoteMapper,
			TravelnotePageMapper travelnotePageMapper, AppraiseMapper appraiseMapper,
			BarrageMapper barrageMapper)
	{
		this.performingRoomsManager = performingRoomsManager;
		this.niyoujiUserMapper = niyoujiUserMapper;
		this.travelnoteMapper = travelnoteMapper;
		this.travelnotePageMapper = travelnotePageMapper;
		this.appraiseMapper = appraiseMapper;
		this.barrageMapper = barrageMapper;
	}
	
	@Override
	public LiveTravelnoteCover[] getLiveTravelnoteCovers()
	{
		int size = 4;
		List<PerformingRoom> performingRooms = performingRoomsManager.getAllPerformingRooms();
		if (performingRooms.size() < size)
		{
			size = performingRooms.size();
		}
		
		LiveTravelnoteCover[] liveTravelnoteCovers = new LiveTravelnoteCover[size];
		for (int i = 0; i < size; i++)
		{
			Travelnote travelnote = performingRooms.get(i).getTravelnote();
			LiveTravelnoteCover liveTravelnoteCover = new LiveTravelnoteCover();
			
			liveTravelnoteCover.setAttentionsCount(travelnote.getAttentionsCount());
			liveTravelnoteCover.setCoverResourceUrl(travelnote.getCoverResourceUrl());
			liveTravelnoteCover.setCoverType(travelnote.getCoverType());
			liveTravelnoteCover.setCreateTime(travelnote.getCreateTime());
			liveTravelnoteCover.setPerformerId(travelnote.getPerformerId());
			liveTravelnoteCover.setAudiencesCount(performingRooms.get(i).getAudiencesCount());
			liveTravelnoteCover.setTravelnoteTitle(travelnote.getTravelnoteTitle());
			
			//这部分得到nickname业务代码已完成哦
			String performerId = travelnote.getPerformerId();
			String nickname = niyoujiUserMapper.getUserNickname(performerId);
			liveTravelnoteCover.setPerformerNickname(nickname);
			
			liveTravelnoteCovers[i] = liveTravelnoteCover;
		}
		
		return liveTravelnoteCovers;
	}
	
	@Override
	public List<FinishedTravelnoteCover> getFinishedTravelnoteCovers(String fromTravelnoteId)
	{
		ArrayList<FinishedTravelnoteCover> finishedTravelnoteCovers = new ArrayList<>();
		ArrayList<Travelnote> travelnotes;
		if (fromTravelnoteId == null)
		{
			travelnotes = travelnoteMapper.getTravelnotes(3);
		}
		else
		{
			int rowCount = travelnoteMapper.getRowCountByTravelnoteId(fromTravelnoteId);
			travelnotes = travelnoteMapper.getTravelnotesFromWhere(rowCount, 2);
		}
		
		for (Travelnote travelnote : travelnotes)
		{
			FinishedTravelnoteCover finishedTravelnoteCover = new FinishedTravelnoteCover();
			finishedTravelnoteCover.setTravelnoteId(travelnote.getTravelnoteId());
			finishedTravelnoteCover.setAttentionsCount(travelnote.getAttentionsCount());
			finishedTravelnoteCover.setCoverResourceUrl(travelnote.getCoverResourceUrl());
			finishedTravelnoteCover.setCoverType(travelnote.getCoverType());
			finishedTravelnoteCover.setCreateTime(travelnote.getCreateTime());
			finishedTravelnoteCover.setPerformerId(travelnote.getPerformerId());
			finishedTravelnoteCover.setTravelnoteTitle(travelnote.getTravelnoteTitle());
			
			finishedTravelnoteCover.setAppraiseCount(appraiseMapper
					.getAppraisesCountByTravelnoteId(travelnote.getTravelnoteId()));
			
			String performerId = travelnote.getPerformerId();
			String nickname = niyoujiUserMapper.getUserNickname(performerId);
			finishedTravelnoteCover.setPerformerNickname(nickname);
			
			ArrayList<TravelnotePage> travelnotePages =
					travelnotePageMapper.getPagesLimitSize(travelnote.getTravelnoteId(), 2);
			int pagesSize = travelnotePages.size();
			finishedTravelnoteCover.setFirstTravelnotePage(travelnotePages.get(0));
			if (pagesSize > 1)
			{
				finishedTravelnoteCover.setSecondTravelnotePage(travelnotePages.get(1));
			}
			
			finishedTravelnoteCovers.add(finishedTravelnoteCover);
		}
		
		return finishedTravelnoteCovers;
	}
	
	@Override
	public Travelnote getTravelnote(String travelnoteId)
	{
		Travelnote travelnote = travelnoteMapper.getTravelnoteById(travelnoteId);
		
		ArrayList<Appraise> appraises =
				appraiseMapper.getAppraisesByTravelnoteId(travelnoteId);
		travelnote.setAppraises(appraises);
		
		ArrayList<TravelnotePage> travelnotePages =
				travelnotePageMapper.getPages(travelnoteId);
		travelnote.setTravelnotePages(travelnotePages);
		
		for (TravelnotePage travelnotePage : travelnotePages)
		{
			ArrayList<Barrage> barrages = barrageMapper
					.getBarragesByTravelnoteIdAndPageId(travelnoteId,
							travelnotePage.getPageId());
			travelnotePage.setBarrages(barrages);
		}
		
		return travelnote;
	}
}
