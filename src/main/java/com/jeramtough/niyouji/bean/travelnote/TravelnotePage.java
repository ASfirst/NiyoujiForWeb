package com.jeramtough.niyouji.bean.travelnote;

public class TravelnotePage
{
	private String pageId;
	private String createTime;
	private String pageType;
	private int themePosition;
	private String resourceUrl;
	
	public String getPageId()
	{
		return pageId;
	}
	
	public void setPageId(String pageId)
	{
		this.pageId = pageId;
	}
	
	public String getCreateTime()
	{
		return createTime;
	}
	
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}
	
	public String getPageType()
	{
		return pageType;
	}
	
	public void setPageType(String pageType)
	{
		this.pageType = pageType;
	}
	
	public int getThemePosition()
	{
		return themePosition;
	}
	
	public void setThemePosition(int themePosition)
	{
		this.themePosition = themePosition;
	}
	
	public String getResourceUrl()
	{
		return resourceUrl;
	}
	
	public void setResourceUrl(String resourceUrl)
	{
		this.resourceUrl = resourceUrl;
	}
}
