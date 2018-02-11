package com.jeramtough.niyouji.business;

import com.jeramtough.niyouji.bean.travelnote.Appraise;
import com.jeramtough.niyouji.dao.mapper.AppraiseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppraiseService implements AppraiseBusiness
{
	private AppraiseMapper appraiseMapper;
	
	@Autowired
	public AppraiseService(AppraiseMapper appraiseMapper)
	{
		this.appraiseMapper = appraiseMapper;
	}
	
	@Override
	public void addAppraise(Appraise appraise)
	{
		appraiseMapper.insertAppraise(appraise);
	}
	
	@Override
	public int getAppraisesCount(String travelnoteId)
	{
		return appraiseMapper.getAppraisesCountByTravelnoteId(travelnoteId);
	}
}
