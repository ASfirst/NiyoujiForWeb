package websocket;

import com.jeramtough.niyouji.entrance.handler.BaseWebSocketHandler;

import java.net.URI;
import java.net.URISyntaxException;

public class AudienceWebSocketClient extends BaseWebSocketClient
{
	private static final String socketHandlerUrl =
			"ws://localhost:8080/niyouji/audienceHandler.do";
	
	public AudienceWebSocketClient() throws URISyntaxException
	{
		super(new URI(socketHandlerUrl));
	}
}
