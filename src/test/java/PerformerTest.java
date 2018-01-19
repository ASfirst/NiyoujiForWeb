import com.jeramtough.jtutil.DateTimeUtil;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.CreatePerformingRoomCommand;
import com.jeramtough.niyouji.component.communicate.factory.PerformerSocketMessageFactory;
import websocket.PerformerWebSocketClient;

public class PerformerTest
{
	public static void main(String[] args)
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
				createPerformingRoomCommand.setCoverResourceUrl(
						"http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/cover_1516105481681.jpg");
				createPerformingRoomCommand.setCoverType("image");
				createPerformingRoomCommand.setCreateTime(DateTimeUtil.getCurrentDateTime());
				createPerformingRoomCommand.setPerformerId("1");
				createPerformingRoomCommand.setTravelnoteTitle("这是测试游记");
				SocketMessage socketMessage = PerformerSocketMessageFactory
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
