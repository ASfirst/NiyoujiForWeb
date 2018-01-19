import com.alibaba.fastjson.JSON;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.action.AudienceCommandActions;
import com.jeramtough.niyouji.bean.socketmessage.command.audience.EnterPerformingRoomCommand;
import websocket.AudienceWebSocketClient;

public class AudienceTest
{
	public static void main(String args[])
	{
		AudienceWebSocketClient client = null;
		try
		{
			client = AudienceWebSocketClient.class.newInstance();
			try
			{
				client.connectBlocking();
				
				EnterPerformingRoomCommand enterPerformingRoomCommand=new
						EnterPerformingRoomCommand();
				enterPerformingRoomCommand.setPerformerId("1");
				
				SocketMessage socketMessage=new SocketMessage(AudienceCommandActions
						.ENTER_PERFORMING_ROOM);
				socketMessage.setCommand(JSON.toJSONString(enterPerformingRoomCommand));
				
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
