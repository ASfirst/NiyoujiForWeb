import com.jeramtough.jtlog3.P;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class TestWebSocketClient extends WebSocketClient
{
	public TestWebSocketClient(URI serverUri)
	{
		super(serverUri);
	}
	
	@Override
	public void onOpen(ServerHandshake handshakedata)
	{
		P.arrive();
	}
	
	@Override
	public void onMessage(String message)
	{
		P.debug(message);
	}
	
	@Override
	public void onClose(int code, String reason, boolean remote)
	{
		P.debug(reason);
	}
	
	@Override
	public void onError(Exception ex)
	{
		ex.printStackTrace();
		P.debug(ex.getMessage());
	}
}
