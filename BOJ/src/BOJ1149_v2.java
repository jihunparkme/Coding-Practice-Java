import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149_v2 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[3];

		int r = 0, g = 0, b = 0;
		for (int i = 0; i < N; i++) {
			
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			r += Math.min(dp[1], dp[2]);
			g += Math.min(dp[0], dp[2]);
			b += Math.min(dp[0], dp[1]);

			dp[0] = r;
			dp[1] = g;
			dp[2] = b;

		}

		System.out.println(Math.min(r, Math.min(g, b)));
	}

}
