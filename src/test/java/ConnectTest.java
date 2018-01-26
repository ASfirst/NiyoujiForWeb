import com.alibaba.fastjson.JSON;
import com.jeramtough.jtutil.DateTimeUtil;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.action.PerformerCommandActions;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.AddPageCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.CreatePerformingRoomCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.PageSetVideoCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.PerformerRebackCommand;
import com.jeramtough.niyouji.component.communicate.factory.PerformerSocketMessageFactory;
import websocket.PerformerWebSocketClient;

public class ConnectTest
{
	
	private static PerformerWebSocketClient client = null;
	
	public static void main(String[] args)
	{
		try
		{
			client = PerformerWebSocketClient.class.newInstance();
			client.reconnectBlocking();
			client.closeBlocking();
			client.reconnectBlocking();
//			client.connectBlocking();
		}
		catch (InstantiationException | IllegalAccessException | InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
}

