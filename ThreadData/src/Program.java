import static java.lang.System.out;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

class Sync {
	private volatile int counter = 0;
	
	public int getCounter() {
		return counter;
	}
	
	public synchronized void increment() {
		counter++;
		
		
		//synchronized(this) {
		//	counter++;
		//}
	}
}

public class Program {
	//static int counter = 0;

	public static void main(String[] args) throws InterruptedException {
		//Object sync = new Object();
		
		Sync sync = new Sync();
		
		AtomicInteger c = new AtomicInteger(0);
		LongAdder l = new LongAdder();
		
		Thread t0 = new Thread( ()-> {
			for(int i=1; i <= 10000; i++)	{
				//out.printf("%s : %d\n", 
				//		Thread.currentThread().getName(), i);
				//counter++;
				//synchronized(sync)
				//{
				//	sync.counter++;
				//}
				sync.increment();
				c.incrementAndGet();
				l.add(1);
			}
		});
		Thread t1 = new Thread( ()-> {
			for(int i=1; i <= 10000; i++) {
				//out.printf("%s : %d\n", 
				//		Thread.currentThread().getName(), i);
				//synchronized(sync) {
				//	sync.counter++;
				//}
				sync.increment();
				c.incrementAndGet();
				l.add(1);
			}
		});
		t0.start();
		t1.start();
		
		t0.join();
		t1.join();
		
		out.println(sync.getCounter());
		out.println(c.get());
		out.println(l.sum());

	}

}
