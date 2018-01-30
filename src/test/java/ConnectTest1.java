import websocket.PerformerWebSocketClient;

public class ConnectTest1
{
	
	private static PerformerWebSocketClient client = null;
	
	public static void main(String[] args)
	{
		try
		{
			client = PerformerWebSocketClient.class.newInstance();
			client.reconnectBlocking();
			client.startHeartbeat();
		}
		catch (InstantiationException | IllegalAccessException | InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
}

