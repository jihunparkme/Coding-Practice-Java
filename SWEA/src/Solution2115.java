import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2115 {

	static int N, M, C, res, map[][], profit[][];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // N : 벌통의 크기
			M = Integer.parseInt(st.nextToken()); // M : 채취할 수 있는 벌통의 수
			C = Integer.parseInt(st.nextToken()); // C : 두 일꾼이 채취할 수 있는 꿀의 최대 양
			profit = new int[N][N]; // 꿀을 담을 용기
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j <N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			res = 0;
			process();
			
			System.out.println("#" + tc + " " + res);
		}

	}

	private static void process() {
		
		// 꿀을 채취할 수 있는 구간에서 얻을 수 있는 최대 수익
		makeProfit();
		// 일꾼 A가 채취할 구간
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				combination(i, j + M, 1, profit[i][j]);
			}
		}
		
	}

	private static void combination(int x, int y, int cnt, int sum) {
		
		if(cnt == 2) {
			res = Math.max(res, sum);
			return;
		}
		// 일꾼 B가 채취할 구간
		for (int i = x; i < N; i++) {
			for (int j = y; j <= N - M; j++) {
				combination(i, j, cnt + 1, sum + profit[i][j]);
			}
			y = 0;
		}
	}

	private static void makeProfit() {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				// 여기서 얻을 수 있는 최대 수익(부분집합)
				profitSubset(i, j, 0, 0, 0);
			}
		}
		
	}

	private static void profitSubset(int i, int j, int cnt, int sum, int totalSum) {
		
		if(sum > C) return;
		if(cnt == M) {
			// 해당 구간에서 최대 수익 갱신
			profit[i][j - M] = Math.max(profit[i][j - M], totalSum);
			return;
		}
		// 이 꿀을 채취해보자
		profitSubset(i, j + 1, cnt + 1, sum + map[i][j], totalSum + map[i][j] * map[i][j]);
		// 이 꿀은 채취 안하고
		profitSubset(i, j + 1, cnt + 1, sum, totalSum);
		
	}

}
