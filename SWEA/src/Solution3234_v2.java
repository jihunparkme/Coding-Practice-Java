import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3234_v2 {

	static int T, N, res, arr[], sel[];
	static boolean used[], isLeft[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			res = 0;
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			sel = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			used = new boolean[N];
			balances(0);
			
			System.out.println("#" + tc + " " + res);
		}

	}

	private static void balances(int cnt) {
		// 모든 추를 올렸다면
		if(cnt == N) {
			isLeft = new boolean[N];
			check(0, 0, 0);
			return;
		}
		 
		// 순열과 부분집합을 동시에 수행
		for (int i = 0; i < N; i++) {
			if(used[i]) continue;
			used[i] = true;
			sel[cnt] = arr[i];
			balances(cnt + 1);
			used[i] = false;
		}
	}

	private static void check(int idx, int sumL, int sumR) {
		
		if(idx == N ) {
			res++;
			return;
		}
		
		// 왼쪽 저울에 사용
		isLeft[idx] = true;
		check(idx + 1, sumL + sel[idx], sumR);
		// 오른쪽 저울에 사용
		isLeft[idx] = false;
		if(sumR + sel[idx] <= sumL)
			check(idx + 1, sumL, sumR + sel[idx]);

	}
}
