package com.jeramtough.niyouji.bean.user;

/**
 * @author 11718
 */
public class NiyoujiUser
{
	private String userId;
	private String nickname;
	private String surfaceImageUrl;
	
	public String getUserId()
	{
		return userId;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	public String getNickname()
	{
		return nickname;
	}
	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	
	public String getSurfaceImageUrl()
	{
		return surfaceImageUrl;
	}
	
	public void setSurfaceImageUrl(String surfaceImageUrl)
	{
		this.surfaceImageUrl = surfaceImageUrl;
	}
}
