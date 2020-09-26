import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10158_v2 {

	static int W, H, C, R, T;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		T = Integer.parseInt(br.readLine()); 
		
		int eR = H - Math.abs(H - (R + T) % (2 * H));
		int eC = W - Math.abs(W - (C + T) % (2 * W)); 
		
		System.out.println(eC + " " + eR);
	}

}
