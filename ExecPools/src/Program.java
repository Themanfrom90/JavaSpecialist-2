
import java.util.concurrent.*;

public class Program {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();
				//Executors.newCachedThreadPool();
				//Executors.newWorkStealingPool(); 
				
				//Executors.newSingleThreadExecutor();
				//Executors.newFixedThreadPool(5);
				//newFixedThreadPool(10); 
				//Executors.newCachedThreadPool();
				// newSingleThreadExecutor();
				//Executors.newWorkStealingPool( Runtime.getRuntime().availableProcessors() );
		
		long t1 = System.currentTimeMillis();
		
		Future<Long>[] tasks = new Future[10];
		for(int i=0; i < 10; i++)
			tasks[i] = pool.submit(new MyCallTask());
		
		//Thread.sleep(61000);
		//pool.shutdown();
		//System.out.println(pool.isShutdown());
		//pool.awaitTermination();
		//System.out.println(pool.isTerminated());
		
		
		//pool.shutdownNow();
		Thread.sleep(100);
		System.out.println(tasks[0].cancel(true));
		
		//Future<Long> t11 = pool.submit(new MyCallTask());
		//Future<Long> t12 = pool.submit(new MyCallTask());
		
		for(Future<Long> t : tasks)
		{
			if (t.isCancelled())
				System.out.println("canceled");
			else
				System.out.println(t.get()); // join
		}
		
		//System.out.println(t11.get());
		//System.out.println(t12.get());
		
		long t2 = System.currentTimeMillis();
		System.out.printf("Time (ms): %d\n", t2-t1);
		
		pool.shutdown();
		

	}

}
