import com.jeramtough.jtlog3.P;
import com.jeramtough.jtutil.DateTimeUtil;
import com.jeramtough.jtutil.IdUtil;
import com.jeramtough.niyouji.bean.socket.SocketMessage;
import com.jeramtough.niyouji.bean.socket.command.CommandFactory;
import com.jeramtough.niyouji.bean.socket.command.CreatePerformingRoomCommand;
import websocket.PerformerWebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

public class TestMain
{
	public static void main(String args[])
	{
		PerformerWebSocketClient client = null;
		try
		{
			client = PerformerWebSocketClient.class.newInstance();
			try
			{
				client.connectBlocking();
				CreatePerformingRoomCommand createPerformingRoomCommand =
						new CreatePerformingRoomCommand();
				createPerformingRoomCommand.setCreateTime(DateTimeUtil.getCurrentDateTime());
				createPerformingRoomCommand.setCoverResourceUrl("http://abc.com");
				createPerformingRoomCommand.setCoverType("image");
				createPerformingRoomCommand.setOwnerId("1");
				createPerformingRoomCommand.setRoomId(IdUtil.getUUID());
				createPerformingRoomCommand.setTravelnoteTitle("都是非法第三方啊是艾丝凡");
				SocketMessage socketMessage = CommandFactory
						.processCreatePerformingRoomSocketMessage(createPerformingRoomCommand);
				client.sendSocketMessage(socketMessage);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}
}
