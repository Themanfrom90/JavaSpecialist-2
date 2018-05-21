import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocket ss = new ServerSocket(1111);
		final ExecutorService p = Executors.newCachedThreadPool(); 
		final AtomicInteger req = new AtomicInteger();

		while (true) {
			final Socket cs = ss.accept();
			p.submit(() -> {
				try {
					out.printf("Accept connection from %s\n", cs.getInetAddress().toString());
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(cs.getInputStream(), Charset.forName("UTF-8")));
					String s = reader.readLine();
					out.printf("%s . %d\n",s,req.incrementAndGet());
					Thread.sleep(100);
					OutputStreamWriter writer = new OutputStreamWriter(cs.getOutputStream(), Charset.forName("UTF-8"));
					writer.write(s.toUpperCase() + "\n"); 
					writer.flush();
					if (s.equals("stop")) {
						ss.close();
						System.exit(0);
					}
				} catch (Exception e) {
					out.println(e.getMessage());

				}
			});

		}

	}

}
