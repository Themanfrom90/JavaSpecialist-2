import static java.lang.System.out;

class MyThread extends Thread {
	@Override
	public void run() {
		for(int i=1; i <= 100; i++)
			out.printf("%s : %d\n", getName(), i);
	}
}

class MySuperThread implements Runnable {
	private int a, b;
	
	public MySuperThread(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public void run() {
		for(int i=a; i <= b; i++) {
			//if (Thread.interrupted()) return;
			if (Thread.currentThread().isInterrupted()) { 
				out.printf("%s is interrupted\n", Thread.currentThread().getName());
				return;
			}
			out.printf("%s : %d\n", 
					Thread.currentThread().getName(), i);
		}
		
		
	}
}




public class Program {

	public static void main(String[] args) {
		MyThread t0 = new MyThread();
		MyThread t1 = new MyThread();
		Thread t2 = new Thread(new MySuperThread(40, 90));
		
		
		Thread t3 = new Thread() {
			@Override
			public void run() {
				for(int i=1; i <= 100; i++)
					out.printf("%s : %d\n", getName(), i);
			}
		};
		
		final int a = 10;
		final int b = 50;
		Thread t4 = new Thread( ()-> {
			for(int i=a; i <= b; i++)
				out.printf("%s : %d\n", 
						Thread.currentThread().getName(), i);
		});
		
		out.printf("t1 state: %s t1 is alive : %s\n", 
				t1.getState(), t1.isAlive());
		
		t0.setPriority(Thread.MAX_PRIORITY);
		t0.setDaemon(true);
		t1.setDaemon(true);
		t2.setDaemon(true);
		t3.setDaemon(true);
		t4.setDaemon(true);
		
		t0.start();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		
		
		out.printf("t1 state: %s t1 is alive : %s\n", 
				t1.getState(), t1.isAlive());
		
		t2.interrupt();
		
		out.println(Thread.currentThread().getName());
		

	}

}
