package com.jeramtough.niyouji.entrance.controller;

import com.jeramtough.niyouji.bean.travelnote.LiveTravelnoteCover;
import com.jeramtough.niyouji.business.LiveTravelnoteBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 11718
 */
@Controller
public class LiveTravelnoteController
{
	@Autowired
	private LiveTravelnoteBusiness liveTravelnoteBusiness;
	
	@RequestMapping("/getLiveTravelnotes")
	@ResponseBody
	public LiveTravelnoteCover[] getLiveTravelnotes()
	{
		/*LiveTravelnoteCover travelnote=new LiveTravelnoteCover();
		travelnote.setCreateTime(DateTimeUtil.getCurrentDateTime());
		travelnote.setPerformerId("1");
		travelnote.setCoverResourceUrl("http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/cover_1516105481681.jpg");
		travelnote.setCoverType("image");
		travelnote.setTravelnoteTitle("测试游记");
		travelnote.setAttentionsCount(0);
		
		LiveTravelnoteCover travelnote1=new LiveTravelnoteCover();
		travelnote1.setCreateTime(DateTimeUtil.getCurrentDateTime());
		travelnote1.setPerformerId("2");
		travelnote1.setCoverResourceUrl("http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/cover_1516105792319.jpg");
		travelnote1.setCoverType("image");
		travelnote1.setTravelnoteTitle("测试游记1");
		travelnote1.setAttentionsCount(100);
		
		LiveTravelnoteCover[] travelnotes=new LiveTravelnoteCover[]{travelnote,travelnote1};*/
		return liveTravelnoteBusiness.getLiveTravelnoteCovers();
	}
}
