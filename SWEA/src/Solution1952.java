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
			plan = new int[13];
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
				plan[i] = Math.min(plan[i - 1] + days * cost[0], plan[i - 1] + cost[1]);
				
				// 3달 이용권을 이용할 경우를 고려
				if(i >= 3) plan[i] = Math.min(plan[i], plan[i - 3] + cost[2]);
			}			

			System.out.println("#" + tc + " " + Math.min(plan[12], cost[3]));
		}

	}
}