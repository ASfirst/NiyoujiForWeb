package websocket;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author 11718
 */
public class AudienceWebSocketClient extends BaseWebSocketClient implements Cloneable
{
	private static final String socketHandlerUrl =
			"ws://" + "127.0.0.1:8080"+ "/niyouji/audienceHandler.do";
	
	public AudienceWebSocketClient() throws URISyntaxException
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
		
		AudienceWebSocketClient client = null;
		try
		{
			client = new AudienceWebSocketClient();
			client.setWebSocketClientListeners(this.getWebSocketClientListeners());
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		return client;
	}
}
