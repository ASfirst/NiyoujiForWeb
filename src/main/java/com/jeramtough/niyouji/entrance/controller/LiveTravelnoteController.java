package com.jeramtough.niyouji.entrance.controller;

import com.jeramtough.jtutil.DateTimeUtil;
import com.jeramtough.niyouji.bean.travelnote.Travelnote;
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
	public Travelnote[] getLiveTravelnotes()
	{
		Travelnote travelnote=new Travelnote();
		travelnote.setCreateTime(DateTimeUtil.getCurrentDateTime());
		travelnote.setPerformerId("1");
		travelnote.setCoverResourceUrl("http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/cover_1516105481681.jpg");
		travelnote.setCoverType("image");
		travelnote.setTravelnoteTitle("测试游记");
		travelnote.setTravelnoteId(null);
		travelnote.setAttentionsCount(0);
		
		Travelnote travelnote1=new Travelnote();
		travelnote1.setCreateTime(DateTimeUtil.getCurrentDateTime());
		travelnote1.setPerformerId("2");
		travelnote1.setCoverResourceUrl("http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/cover_1516105792319.jpg");
		travelnote1.setCoverType("image");
		travelnote1.setTravelnoteTitle("测试游记1");
		travelnote1.setTravelnoteId(null);
		travelnote1.setAttentionsCount(100);
		
		Travelnote[] travelnotes=new Travelnote[]{travelnote,travelnote1};
		
		return travelnotes;
	}
}
