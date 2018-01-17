import com.jeramtough.jtutil.DateTimeUtil;
import com.jeramtough.jtutil.IdUtil;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessageFactory;
import com.jeramtough.niyouji.bean.socketmessage.command.client.CreatePerformingRoomCommand;
import websocket.PerformerWebSocketClient;

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
				createPerformingRoomCommand.setTravelnoteId(IdUtil.getUUID());
				createPerformingRoomCommand.setTravelnoteTitle("都是非法第三方啊是艾丝凡");
				SocketMessage socketMessage = SocketMessageFactory
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
