import com.alibaba.fastjson.JSON;
import com.jeramtough.jtlog3.P;
import com.jeramtough.jtutil.DateTimeUtil;
import com.jeramtough.niyouji.bean.socket.SocketMessage;
import com.jeramtough.niyouji.bean.socket.command.CommandFactory;
import com.jeramtough.niyouji.bean.socket.command.CreatePerformingRoomCommand;

import java.net.URI;
import java.net.URISyntaxException;

public class Test
{
	@org.junit.Test
	public void test()
	{
		CreatePerformingRoomCommand command=new CreatePerformingRoomCommand();
		command.setCreateTime(DateTimeUtil.getCurrentDateTime());
		SocketMessage socketMessage=new SocketMessage();
		socketMessage.setCommand("sdfsadfsadf");
		socketMessage.setCommandAction(1);
		socketMessage.setCommand1(command);
		System.out.println(JSON.toJSONString(socketMessage));
		
		
	}
}
