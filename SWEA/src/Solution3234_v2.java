import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution3234_v2 {

	static int T, N, res, arr[], weight[];
	static boolean used[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			res = 0;
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			weight = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			used = new boolean[N];
			balances(0);
			
			System.out.println("#" + tc + " " + res);
		}

	}
	
	/**
	 * 추 줄 세우기
	 * @param cnt	순열에 사용된 추의 개수
	 */
	private static void balances(int cnt) {
		// 모든 추를 세웠다면
		if(cnt == N) {
			check(0, 0, 0);
			return;
		}
		 
		// 세울 수 있는 추의 경우(순열)
		// N개 중 N개를 세울 경우. nPn = N!
		for (int i = 0; i < N; i++) {
			// 이미 사용된 추라면 pass
			if(used[i]) continue;
			// 이 추를 사용해보자.
			used[i] = true;
			weight[cnt] = arr[i];
			balances(cnt + 1);
			// 지금 사용 안 해볼래.
			used[i] = false;
		}
	}

	/**
	 * 세워진 추를 사용해서 저울에 올려보자. 
	 * @param idx	사용할 추의 index
	 * @param sumL	왼쪽 저울에 올려진 추들의 무게
	 * @param sumR	오른쪽 저울에 올려진 추들의 무게
	 */
	private static void check(int idx, int sumL, int sumR) {
		// 모든 추를 저울에 다 올렸다면
		if(idx == N ) {
			res++;
			return;
		}
		// 왼쪽 저울에 해당 idx 추를 올려보자.
		check(idx + 1, sumL + weight[idx], sumR);
		// 오른쪽 저울에 올려진 추들의 무게가 왼쪽에 올라가 있는 추들의 무게보다 작다면
		// 오른쪽에 해당 idx 추를 올려보자.
		if(sumR + weight[idx] <= sumL)
			check(idx + 1, sumL, sumR + weight[idx]);
	}
}