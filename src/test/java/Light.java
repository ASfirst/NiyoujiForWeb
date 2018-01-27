public class Light
{
	/**
	 * 路口名
	 */
	private String name;
	
	/**
	 * 红灯时长
	 */
	private int redTime;
	
	/**
	 * 绿灯时长
	 */
	private int greenTime;
	
	/**
	 * 黄灯时长
	 */
	private int yellowTime;
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getRedTime()
	{
		return redTime;
	}
	
	public void setRedTime(int redTime)
	{
		this.redTime = redTime;
	}
	
	public int getGreenTime()
	{
		return greenTime;
	}
	
	public void setGreenTime(int greenTime)
	{
		this.greenTime = greenTime;
	}
	
	public int getYellowTime()
	{
		return yellowTime;
	}
	
	public void setYellowTime(int yellowTime)
	{
		this.yellowTime = yellowTime;
	}
}
