import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution9229 {

	static int N, M, res, R = 2, snack[];
	
	public static void process(int idx, int cnt, int kg) {
		if(kg > M) return;
		if(cnt == R && idx <= N) {
			// 두 개 고르고, 들 수 있는 무게라면
			if(kg <= M)
				res = Math.max(res, kg);
			return;
		}
		if(idx == N) return;
		
		process(idx + 1, cnt + 1, kg + snack[idx]);
		process(idx + 1, cnt, kg);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			snack = new int[N];
			
			// N개의 과자 봉지 무게
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) 
				snack[i] = Integer.parseInt(st.nextToken());
			
			res = -1;
			process(0, 0, 0);
			
			System.out.println("#" + tc + " " + res);
		}
	}

}

