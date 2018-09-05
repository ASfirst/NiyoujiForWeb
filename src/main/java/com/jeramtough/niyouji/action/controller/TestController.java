package com.jeramtough.niyouji.action.controller;

import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.jeramtough.jtlog3.P;
import com.jeramtough.jtutil.DateTimeUtil;
import com.jeramtough.niyouji.bean.travelnote.Barrage;
import com.jeramtough.niyouji.bean.travelnote.Travelnote;
import com.jeramtough.niyouji.bean.travelnote.TravelnotePage;
import com.jeramtough.niyouji.dao.mapper.BarrageMapper;
import com.jeramtough.niyouji.dao.mapper.NiyoujiUserMapper;
import com.jeramtough.niyouji.dao.mapper.TravelnoteMapper;
import com.jeramtough.niyouji.dao.mapper.TravelnotePageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
	private NiyoujiUserMapper niyoujiUserMapper;
	
	@Autowired
	public TestController(TravelnotePageMapper travelnotePageMapper,
			TravelnoteMapper travelnoteMapper, BarrageMapper barrageMapper,
			NiyoujiUserMapper niyoujiUserMapper)
	{
		this.travelnotePageMapper = travelnotePageMapper;
		this.travelnoteMapper = travelnoteMapper;
		this.barrageMapper = barrageMapper;
		this.niyoujiUserMapper = niyoujiUserMapper;
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
		barrage.setContent("\\xF0\\x9F\\x98\\x98\\xF0\\x9F");
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
		return niyoujiUserMapper.getUserNickname(userId);
	}
	
	@RequestMapping("/getRowCountByTravelnoteId")
	@ResponseBody
	public String getRowCountByTravelnoteId(
			@RequestParam(value = "travelnoteId", required = true) String travelnoteId)
	{
		return travelnoteMapper.getRowCountByTravelnoteId(travelnoteId) + "";
	}
	
	@PostMapping("/SetCarAccountRecharge")
	@ResponseBody
	public String setCarAccountRecharge()
	{
		String b="{\\\"result\\\":\\\"ok\\\"}";
		String a= "{\"serverinfo\":\""+b+"\"}";
		return a;
	}
	@PostMapping("/SetCarMove")
	@ResponseBody
	public String SetCarMove()
	{
		String b="{\\\"result\\\":\\\"ok\\\"}";
		String a= "{\"serverinfo\":\""+b+"\"}";
		return a;
	}
	
	@PostMapping("/GetCarAccountBalance")
	@ResponseBody
	public String getCarAccountBalance()
	{
		return "{\"serverinfo\":\"{\\\"Balance\\\":800}\"}";
	}
	
	@PostMapping("/GetTrafficLightConfigAction")
	@ResponseBody
	public String GetTrafficLightConfigAction()
	{
		String b="{\\\"RedTime\\\":\\\"25\\\", \\\"GreenTime\\\":\\\"55\\\", \\\"YellowTime\\\":\\\"5\\\"}";
		String a= "{\"serverinfo\":\""+b+"\"}";
		return a;
	}
	
	@PostMapping("/GetAllSense")
	@ResponseBody
	public String GetAllSense()
	{
		String b="{\\\"pm2.5\\\":4,\\\"co2\\\":813,\\\"temperature\\\":19," +
				"\\\"LightIntensity\\\":3000,\\\"humidity\\\":40}";
		String a= "{\"serverinfo\":\""+b+"\"}";
		return a;
	}
	
	@PostMapping("/GetRoadStatus")
	@ResponseBody
	public String GetRoadStatus()
	{
		String b="{\\\"Status\\\":3}：";
		String a= "{\"serverinfo\":\""+b+"\"}";
		return a;
	}
}
