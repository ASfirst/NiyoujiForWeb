import com.alibaba.fastjson.JSON;
import com.jeramtough.jtutil.DateTimeUtil;
import com.jeramtough.niyouji.bean.socketmessage.SocketMessage;
import com.jeramtough.niyouji.bean.socketmessage.action.PerformerCommandActions;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.AddPageCommand;
import com.jeramtough.niyouji.bean.socketmessage.command.performer.CreatePerformingRoomCommand;
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
				SocketMessage socketMessage =
						new SocketMessage(PerformerCommandActions.CREATE_PERFORMING_ROOM);
				socketMessage.setCommand(JSON.toJSONString(createPerformingRoomCommand));
				
				client.sendSocketMessage(socketMessage);
				
				AddPageCommand addPageCommand = new AddPageCommand();
				addPageCommand.setCreateTime(DateTimeUtil.getCurrentDateTime());
				addPageCommand.setPageType("picture_and_word");
				addPageCommand.setPerformerId("1");
				SocketMessage socketMessage1 =
						new SocketMessage(PerformerCommandActions.ADDED_PAGE);
				socketMessage1.setCommand(JSON.toJSONString(addPageCommand));
				client.sendSocketMessage(socketMessage1);
				client.sendSocketMessage(socketMessage1);
				
				addPageCommand.setPageType("video");
				socketMessage1.setCommand(JSON.toJSONString(addPageCommand));
				
				client.sendSocketMessage(socketMessage1);
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
