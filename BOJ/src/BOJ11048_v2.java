import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11048_v2 {

	static int R, C, dp[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		dp = new int[R+1][C+1];
		
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dp[R][C]);
	}

}
