import java.net.URI;
import java.net.URISyntaxException;

public class TestMain
{
	public static void main(String args[])
	{
		try
		{
			String urlString1="ws://localhost:8080/niyouji/testHandler.do";
			TestWebSocketClient client =
					new TestWebSocketClient(new URI
							(urlString1));
			client.connect();
//			client.send("fasdfsafd");
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
	}
}
