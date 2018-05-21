import static java.lang.System.out;

public class Program {

	public static void main(String[] args) throws InterruptedException {
		Thread t0 = new Thread( ()-> {
			for(int i=1; i <= 100; i++)
				out.printf("%s : %d\n", 
						Thread.currentThread().getName(), i);
		});
		Thread t1 = new Thread( ()-> {
			try {
				t0.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(int i=1; i <= 100; i++)
				out.printf("%s : %d\n", 
						Thread.currentThread().getName(), i);
		});
		
		t0.start();
		
		t1.start();
		//Thread.sleep(1000);
		
		//Thread.sleep(2000);
		t0.join();
		t1.join();
		
		
		//Thread.currentThread().join(); // deadlock
		
		out.println(Thread.currentThread().getName());

	}

}
