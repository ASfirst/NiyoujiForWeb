package websocket;

import com.alibaba.fastjson.JSON;
import com.jeramtough.jtlog3.WithLogger;
import com.jeramtough.niyouji.bean.socket.SocketMessage;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class BaseWebSocketClient extends WebSocketClient implements WithLogger
{
	public BaseWebSocketClient(URI serverUri)
	{
		super(serverUri);
	}
	
	@Override
	public void onOpen(ServerHandshake handshakedata)
	{
		getP().info("open a new connection with server");
	}
	
	@Override
	public void onMessage(String message)
	{
		getP().info("accept a message from server [" + message + "]");
	}
	
	@Override
	public void onClose(int code, String reason, boolean remote)
	{
		getP().warn("close the connection with server, because " + code + ": " + reason);
	}
	
	@Override
	public void onError(Exception ex)
	{
		getP().error("have a exception for [" + ex.getMessage() + "]");
	}
	
	public void sendSocketMessage(SocketMessage socketMessage)
	{
		this.send(JSON.toJSONString(socketMessage));
	}
}
