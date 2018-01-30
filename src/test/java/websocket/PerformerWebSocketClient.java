package websocket;


import java.net.URI;
import java.net.URISyntaxException;

public class PerformerWebSocketClient extends BaseWebSocketClient implements Cloneable
{
	private static final String socketHandlerUrl =
			"ws://" + "127.0.0.1:8080" + "/niyouji/performerHandler" + ".do";
	
	public PerformerWebSocketClient() throws URISyntaxException
	{
		super(new URI(socketHandlerUrl));
	}
	
	@Override
	public Object clone()
	{
		try
		{
			super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		
		PerformerWebSocketClient client = null;
		try
		{
			client = new PerformerWebSocketClient();
			client.setWebSocketClientListeners(this.getWebSocketClientListeners());
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		return client;
	}
}
