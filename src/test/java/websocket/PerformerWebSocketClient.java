package websocket;

import java.net.URI;
import java.net.URISyntaxException;

public class PerformerWebSocketClient extends BaseWebSocketClient
{
	private static final String socketHandlerUrl =
			"ws://localhost:8080/niyouji/performerHandler.do";
	
	public PerformerWebSocketClient() throws URISyntaxException
	{
		super(new URI(socketHandlerUrl));
	}
}
