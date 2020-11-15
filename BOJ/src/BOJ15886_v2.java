import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15886_v2 {

	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		String map = br.readLine();
		
		int res = 0;
		for (int i = 0; i < N - 1; i++) {
			if(map.charAt(i) == 'E' && map.charAt(i + 1) == 'W') res++; 
		}
		
		System.out.println(res);
	}

}
