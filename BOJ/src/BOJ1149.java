import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N + 1][4];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());  
			int g = Integer.parseInt(st.nextToken());  
			int b = Integer.parseInt(st.nextToken());  
			
			dp[i][1] = Math.min(dp[i-1][2], dp[i-1][3]) + r; 
			dp[i][2] = Math.min(dp[i-1][1], dp[i-1][3]) + g;
			dp[i][3] = Math.min(dp[i-1][1], dp[i-1][2]) + b;
		}
		
		int res = 987654321;
		for (int i = 1; i <= 3; i++) 
			res = Math.min(res, dp[N][i]);
		
		System.out.println(res);
	}
	
}
