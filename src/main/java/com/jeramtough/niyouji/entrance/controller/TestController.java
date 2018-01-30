package com.jeramtough.niyouji.entrance.controller;

import com.jeramtough.jtlog3.P;
import com.jeramtough.jtutil.DateTimeUtil;
import com.jeramtough.niyouji.bean.travelnote.Barrage;
import com.jeramtough.niyouji.bean.travelnote.Travelnote;
import com.jeramtough.niyouji.bean.travelnote.TravelnotePage;
import com.jeramtough.niyouji.dao.mapper.BarrageMapper;
import com.jeramtough.niyouji.dao.mapper.PrimaryUserMapper;
import com.jeramtough.niyouji.dao.mapper.TravelnoteMapper;
import com.jeramtough.niyouji.dao.mapper.TravelnotePageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 11718
 */
@Controller
public class TestController
{
	private TravelnotePageMapper travelnotePageMapper;
	private TravelnoteMapper travelnoteMapper;
	private BarrageMapper barrageMapper;
	private PrimaryUserMapper primaryUserMapper;
	
	@Autowired
	public TestController(TravelnotePageMapper travelnotePageMapper,
			TravelnoteMapper travelnoteMapper, BarrageMapper barrageMapper,
			PrimaryUserMapper primaryUserMapper)
	{
		this.travelnotePageMapper = travelnotePageMapper;
		this.travelnoteMapper = travelnoteMapper;
		this.barrageMapper = barrageMapper;
		this.primaryUserMapper = primaryUserMapper;
	}
	
	@RequestMapping("/hello")
	@ResponseBody
	public String test()
	{
		P.arrive();
		return "hello";
	}
	
	@RequestMapping("/inertBarrageTest")
	@ResponseBody
	public String inertBarrageTest()
	{
		P.arrive();
		Barrage barrage = new Barrage();
		barrage.setNickname("asdfsa");
		barrage.setCreateTime(DateTimeUtil.getCurrentDateTime());
		barrage.setContent("dsfsa");
		barrage.setIsPerformers(true);
		barrage.setPageId("2");
		barrage.setTravelnoteId("6");
		
		barrageMapper.insertBarrage(barrage);
		
		return "ok";
	}
	
	@RequestMapping("/inertPageTest")
	@ResponseBody
	public String inertPageTest()
	{
		P.arrive();
		TravelnotePage travelnotePage = new TravelnotePage();
		travelnotePage.setBackgroundMusicPath("/opt/abc.mp3");
		travelnotePage.setTravelnoteId("1");
		travelnotePage.setThemePosition(1);
		travelnotePage.setTextContent("dasdfdsafsadfsadfsafddsa");
		travelnotePage.setResourceUrl("http://dfssadf.com");
		travelnotePage.setPageType("picandword");
		travelnotePage.setCreateTime(DateTimeUtil.getCurrentDateTime());
		
		travelnotePageMapper.insertTravelnotePage(travelnotePage);
		
		return "ok";
	}
	
	@RequestMapping("/inertTravelnoteTest")
	@ResponseBody
	public String inertTravelnoteTest()
	{
		P.arrive();
		Travelnote travelnote = new Travelnote();
		travelnote.setAttentionsCount(44);
		travelnote.setTravelnoteTitle("test travelnote");
		travelnote.setCoverType("image");
		travelnote.setCoverResourceUrl("http://dsfadsf");
		travelnote.setPerformerId("3");
		travelnote.setCreateTime(DateTimeUtil.getCurrentDateTime());
		
		travelnoteMapper.insertTravelnote(travelnote);
		
		return "ok";
	}
	
	@RequestMapping("/getNicknameTest")
	@ResponseBody
	public String getNicknameTest(
			@RequestParam(value = "userId", required = false, defaultValue = "0") String userId)
	{
		return primaryUserMapper.getUserNickname(userId);
	}
}
