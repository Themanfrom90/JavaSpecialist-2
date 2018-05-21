import static java.lang.System.out;

import java.util.concurrent.locks.*;

public class Program {
	
	static int counter; 

	public static void main(String[] args) {
		
		Lock lock = new ReentrantLock();
		Condition greater50 = lock.newCondition();
		Condition greater70 = lock.newCondition();
		
		
		Thread t0 = new Thread( ()-> {
			for(int i=1; i <= 100; i++) {
				out.printf("%s : %d\n", 
						Thread.currentThread().getName(), i);
				lock.lock();
				try {
					counter = i;
					if (i == 50) greater50.signal();
					if (i == 70) greater70.signal();
				}
				finally {
					lock.unlock();
				}
			}
		});
		Thread t1 = new Thread( ()-> {
			 // >= 50
			
			lock.lock();
			try {
				while (counter < 50)
					greater50.awaitUninterruptibly();
			}
			finally {
				lock.unlock();
			}
			for(int i=1; i <= 100; i++)
				out.printf("%s : %d\n", 
						Thread.currentThread().getName(), i);
		});
		Thread t2 = new Thread( ()-> {
			 // >= 70
			lock.lock();
			try {
				while (counter < 70)
					greater70.awaitUninterruptibly();
			}
			finally {
				lock.unlock();
			}
			
			for(int i=1; i <= 100; i++)
				out.printf("%s : %d\n", 
						Thread.currentThread().getName(), i);
		});
		
		t0.start();
		t1.start();
		t2.start();
	}

}
