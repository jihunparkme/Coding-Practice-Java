import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3282_v2 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 물건 개수
			int W = Integer.parseInt(st.nextToken()); // 가방 부피
			int[] dp = new int[W + 1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());

				int weight = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());

				for (int j = W; j >= weight; j--) {
					dp[j] = Math.max(dp[j], dp[j - weight] + value);
				}

			}

			System.out.println("#" + tc + " " + dp[W]);
		}
	}

}