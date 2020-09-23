import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11726 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[1010];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;
		
		for (int i = 4; i <= N; i++) {
			dp[i] = (dp[i-2] + dp[i-1]) % 10007;
		}

		System.out.println(dp[N]);
	}

}
