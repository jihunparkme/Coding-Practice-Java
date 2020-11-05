import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2115 {

	static int N, M, C, res, tmpMax, map[][], container[][];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // N : 벌통의 크기
			M = Integer.parseInt(st.nextToken()); // M : 채취할 수 있는 벌통의 수
			C = Integer.parseInt(st.nextToken()); // C : 두 일꾼이 채취할 수 있는 꿀의 최대 양
			container = new int[2][M]; // 꿀을 담을 용기
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j <N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			process(0, 0, 0);
			
			System.out.println("#" + tc + " " + res);
		}
		
		

		// 가로로 연속되도록 M개의 벌통을 선택
			// 두 명의 일꾼이 선택한 벌통이 겹치면 안됨
			// 벌통에서 채취한 꿀은 하나의 용기에
			// 벌통에 있는 모든 꿀을 한번에 채취
			// 꿀의 양을 초과할 경우 최대를 선택(조합)
		
		// 수익은 꿀의 양의 제곱만큼

	}

	private static void process(int sx, int sy, int cnt) {
		
		// 두 일꿀이 벌통을 선택했다면
		if(cnt == 2) {
			int tmp = 0;
			
			tmpMax = 0;
			getProfit(0, 0, 0, 0); // 0번 일꾼
			tmp += tmpMax;
			
			tmpMax = 0;
			getProfit(1, 0, 0, 0); // 1번 일꾼
			tmp += tmpMax;		
			
			res = Math.max(res, tmp);
			
			return;
		}
		
		if(sy >= N) return;
		
		for (int i = sx; i < N; i++) {
			for (int j = sy; j < N - M + 1; j++) {
				// 벌통에서 채취한 꿀은 하나의 용기에 담자
				for (int k = 0; k < M; k++) {
					container[cnt][k] = map[i][j + k];
				}
				
				process(i, j + M, cnt + 1);
			}
		}
		
	}

	private static void getProfit(int num, int idx, int sum, int profit) {
		 
		// 꿀의 양을 초과할 경우
		if(sum > C) return;
		// 담을 꿀을 모두 선택했다면
		if(idx == M) {
			tmpMax = Math.max(tmpMax, profit);
			return;
		}
		
		int tmp = container[num][idx]; 
		int pf = container[num][idx] * container[num][idx];		
		
		getProfit(num, idx + 1, sum + tmp, profit + pf);
		getProfit(num, idx + 1, sum, profit);
	}

	

}
