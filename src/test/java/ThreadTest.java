import java.lang.management.ThreadMXBean;
import java.util.concurrent.*;

public class ThreadTest
{
	public static void main(String[] args)
	{
		ExecutorService executorService =
				new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
						new LinkedBlockingQueue<Runnable>());
		ExecutorService executorService1 = Executors.newCachedThreadPool();
		
		MyThread myThread=new MyThread("A");
		MyThread myThread1=new MyThread("B");
		
		synchronized (ThreadTest.class)
		{
			myThread.start();
			myThread1.start();
		}
	}
	
	static class MyThread extends Thread
	{
		
		public MyThread(String name)
		{
			super(name);
		}
		
		@Override
		public synchronized void run()
		{
			for (int i = 0; i < 100; i++)
			{
				System.out.println(getName()+i);
			}
		}
		
		public synchronized void a(int i)
		{
			System.out.println(getName()+i);
		}
	}
	
	
}
