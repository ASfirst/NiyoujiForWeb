import java.util.concurrent.*;

public class ThreadTest
{
	public static void main(String[] args)
	{
		ExecutorService executorService =
				new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
						new LinkedBlockingQueue<Runnable>());
		ExecutorService executorService1 = Executors.newCachedThreadPool();
		for (int i=0;i<100;i++)
		{
			int finalI = i;
			executorService1.submit(()->{
				System.out.println(finalI);
			});
		}
	}
}
