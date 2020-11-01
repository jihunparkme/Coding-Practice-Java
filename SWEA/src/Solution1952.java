import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1952 {

	static int[] price, plan;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			price = new int[4]; // 1일, 1달, 3달, 1년
			plan = new int[13];
			// 가격 정보
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			// 계획 정보
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			System.out.println("#" + tc + " " + process());
		}

	}

	private static int process() {
		
		int[][] dp = new int[3][13];
		
		// 1일 이용권만 사용할 경우 선처리
		for (int i = 1; i <= 12; i++) {
			dp[0][i] = dp[0][i - 1] + plan[i] * price[0];
		}
		
		// 1달 , 3달 이용권을 사용할 수 있을 경우
		for (int p = 1; p < 3; p++) {
			for (int m = 1; m <= 12; m++) {
				// 이전 월까지의 비용에 1달 이용권 비용을 더한 값이 더 작다면
				if(plan[m] > 0) {
					if(dp[p - 1][m] > dp[p][m-1] + price[p]) {
						// 그냥 하루치를 사는게 이득일 경우
						if(dp[p][m-1] + price[p] > dp[p][m-1] + price[p - 1] * plan[m])
							dp[p][m] = dp[p][m-1] + price[p - 1];
						else dp[p][m] = dp[p][m-1] + price[p];
					} else {
						dp[p][m] = dp[p-1][m]; 
					}
				} else {
					dp[p][m] = dp[p][m - 1]; 
				}
			}
		}

		// 1년 이용권이랑 비교 후 return
		return dp[2][12] > price[3] ? price[3] : dp[2][12];
	}

}

/*
 * 이용권 가격 1일 : 10원 1달 : 40원 (매달 1일부터 시작) 3달 : 100원 (연속된 3달, 매달 1일부터 시작) 1년 :
 * 300원 (매년 1월 1일부터 시작)
 */