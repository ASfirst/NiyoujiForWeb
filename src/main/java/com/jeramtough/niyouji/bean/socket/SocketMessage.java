package com.jeramtough.niyouji.bean.socket;

/**
 * @author 11718
 */
public class SocketMessage
{
	private int commandAction;
	
	private String command;
	
	
	
	public int getCommandAction()
	{
		return commandAction;
	}
	
	public void setCommandAction(int commandAction)
	{
		this.commandAction = commandAction;
	}
	
	public String getCommand()
	{
		return command;
	}
	
	public void setCommand(String command)
	{
		this.command = command;
	}
	
}
