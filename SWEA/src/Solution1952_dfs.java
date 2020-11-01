import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1952_dfs {

	static int[] cost, plan;
	static int res;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			cost = new int[4]; // 1일, 1달, 3달, 1년
			plan = new int[13];
			// 가격 정보
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			// 계획 정보
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			// 기본 최소 비용은 1년 이용권
			res = cost[3];
			
			process(1, 0);
			
			System.out.println("#" + tc + " " + res);
		}

	}

	private static void process(int month, int sum) {
		// 이미 최소 비용을 넘어가버리면 cut
		if(res <= sum) return;
		
		if(month > 12) {
			res = Math.min(res, sum);
			return;
		}
		
		// 이번 달에 이용 계획이 없다면
		if(plan[month] == 0) {
			process(month + 1, sum);
		} else {
			// 1일 이용권을 이용할 경유
			process(month + 1, sum + cost[0] * plan[month]);
			
			// 1달 이용권을 이용할 경우
			process(month + 1, sum + cost[1]);
			
			// 3달 이용권을 이용할 경우
			process(month + 3, sum + cost[2]);
		}
	}

}

/*
 * 이용권 가격 
 * 1일 : 10원 
 * 1달 : 40원 (매달 1일부터 시작) 
 * 3달 : 100원 (연속된 3달, 매달 1일부터 시작) 
 * 1년 : 300원 (매년 1월 1일부터 시작)
 */