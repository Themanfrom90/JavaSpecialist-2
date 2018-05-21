import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class Program {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		out.println("Starting process...");
		Process p = Runtime.getRuntime().exec("cmd /C dir");
		
		//p.getOutputStream()
		BufferedReader reader = new BufferedReader(
			new InputStreamReader(p.getInputStream(), 
					Charset.forName("cp866")));
		
		String s;
		while ( (s = reader.readLine()) != null) {
			out.println(s);
		}
		
		p.waitFor();
		int exitValue = p.exitValue();
		
		out.printf("Process exit with code : %d\n", exitValue);
		
			
		
		

	}

}
