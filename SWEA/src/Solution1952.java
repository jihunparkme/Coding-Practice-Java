import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1952 {

	static int[] cost, plan;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			// 1일, 1달, 3달, 1년 비용
			cost = new int[4];
			// 12월에 3개월 이용권을 구매할 경우를 생각해서
			plan = new int[15];
			// 가격 정보
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			// 계획 정보
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				int days = Integer.parseInt(st.nextToken());
				// 하루 이용권과 한달 이용권 중 최소 비용을 저장
				plan[i] = Math.min(days * cost[0], cost[1]);
			}
			// 
			for (int i = 11; i >= 1; i--) {
				// 3달 이용권을 이용할 경우와 아닐 경우의 최소 누적 비용을 저장 
				plan[i] = Math.min(plan[i] + plan[i + 1], plan[i + 3] + cost[2]);
			}

			System.out.println("#" + tc + " " + Math.min(plan[1], cost[3]));
		}

	}
}

/*
 * 이용권 가격 1일 : 10원 1달 : 40원 (매달 1일부터 시작) 3달 : 100원 (연속된 3달, 매달 1일부터 시작) 1년 :
 * 300원 (매년 1월 1일부터 시작)
 */