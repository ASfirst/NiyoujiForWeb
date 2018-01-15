package com.jeramtough.niyouji.bean.socket;

/**
 * @author 11718
 */
public class SocketMessage
{
	private int commandAction;
	
	private String command;
	private Object command1;
	
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
	
	public Object getCommand1()
	{
		return command1;
	}
	
	public void setCommand1(Object command1)
	{
		this.command1 = command1;
	}
}
