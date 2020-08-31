import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18512 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int X = Integer.parseInt(st.nextToken()); 
		int Y = Integer.parseInt(st.nextToken()); 
		int P1 = Integer.parseInt(st.nextToken());	
		int P2 = Integer.parseInt(st.nextToken());	
		
		int res = -1;
		
		out:for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(P1 + X * i < P2 + Y * j) break;
				if(P1 + X * i == P2 + Y * j) {
					res = P1 + X * i; 
					break out;
				}
			}
		}
		
		System.out.println(res);
		
	}
	
}
