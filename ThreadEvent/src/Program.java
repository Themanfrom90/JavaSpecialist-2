import static java.lang.System.out;

public class Program {

	public static void main(String[] args) throws InterruptedException {
		class Sync {
			int counter;
		} 
		Sync sync = new Sync();

		Thread t0 = new Thread(() -> {
			for (int i = 1; i <= 100; i++) {
				out.printf("%s : %d\n", Thread.currentThread().getName(), i);
				synchronized(sync) {
					sync.counter = i;
					sync.notifyAll();
					if (i == 50)
						try {
							sync.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
		});
		Thread t1 = new Thread(() -> {
			//Thread.holdsLock(sync)
			try {
				synchronized (sync) {
					while(sync.counter < 50 && t0.getState() != Thread.State.NEW)
						sync.wait(10000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for (int i = 1; i <= 100; i++) {
				out.printf("%s : %d\n", Thread.currentThread().getName(), i);
				if (i == 1)
					synchronized(sync) {
						sync.notify();
					}

				
			}
		});

		t0.start();
		//Thread.sleep(100);
		t1.start();

	}

}
