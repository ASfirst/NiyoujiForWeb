package com.jeramtough.niyouji.bean.socketmessage.command.performer;

/**
 * @author 11718
 *         on 2018  January 18 Thursday 03:01.
 */

public class PageSetBackgroundMusicCommand extends PerformerCommand
{
	private int position;
	private String musicPath;
	
	public int getPosition()
	{
		return position;
	}
	
	public void setPosition(int position)
	{
		this.position = position;
	}
	
	public String getMusicPath()
	{
		return musicPath;
	}
	
	public void setMusicPath(String musicPath)
	{
		this.musicPath = musicPath;
	}
}
