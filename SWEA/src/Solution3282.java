import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution3282 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 물건 개수
			int W = Integer.parseInt(st.nextToken()); // 가방 부피
			int[][] dp = new int[N + 1][W + 1];
			Item[] bag = new Item[N+1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				
				bag[i] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= W; j++) {
					// i번째 물건의 무게가 가방 부피보다 크다면
					if(bag[i].weight > j) dp[i][j] = dp[i-1][j];
					else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-bag[i].weight] + bag[i].value);
				}
			}

			System.out.println("#" + tc + " " + dp[N][W]);
		}
	}

}

class Item {
	int weight;
	int value;

	public Item(int weight, int value) {
		super();
		this.weight = weight;
		this.value = value;
	}

}