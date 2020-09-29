import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2292 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int idx = 1;
		int jump = 1;
		while(true) {
			if(N > 6 * idx + 1) {
				jump++;
				idx += jump;
			} else {
				break;
			}
		}
		
		if(N == 1) System.out.println('1');
		else System.out.println(jump + 1);
	}

}
