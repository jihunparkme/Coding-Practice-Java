import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15961 {

	static int N, D, K, C, res, cnt, dishs[];
	static int ate[];
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());	// 접시 수
		D = Integer.parseInt(st.nextToken());	// 초밥의 가짓수
		K = Integer.parseInt(st.nextToken());	// 연속해서 먹는 접시의 수 
		C = Integer.parseInt(st.nextToken());	// 쿠폰 번호
		
		dishs = new int[N];
		ate = new int[D + 1];
		for (int i = 0; i < N; i++) 
			dishs[i] = Integer.parseInt(br.readLine());

		// 0번 idx 접시에 있는 초밥들부터 먼저 먹어보자.
		for (int i = 0; i < K; i++) {
			// 먹었던 초밥이 아니면
			if(ate[dishs[i]] == 0) {
				res++;
			}
			ate[dishs[i]]++;
		}
		// 쿠폰에 적힌 초밥이 새로운 종류라면
		cnt = res;
		res = (ate[C] == 0) ? cnt + 1 : cnt;
		
		// 이 상태에서 추가로 하나씩 먹어보자
		belt();
		
		System.out.println(res);
	}

	private static void belt() {

		// 시작점
		int start = K;
		// 회전 초밥은 계속 돌아가고 있다.
		while(true) {
			
			// 이전에 먹었던건 퉤퉤
			ate[dishs[(start - K) % N]]--;
			// 뱉었는데 먹은적이 없으면
			if(ate[dishs[(start - K) % N]] == 0) cnt -= 1;
			
			// 다음에 위치한 초밥이 먹었던게 아니면 냠냠
			if(ate[dishs[start % N]] == 0) {
				cnt++;
			}
			ate[dishs[start % N]]++;
			
			// 얼마나 다양한 종류를 먹었나
			// 쿠폰에 적힌 초밥이 안 먹어본거면 종류라면 + 1
			res = Math.max(res, (ate[C] == 0) ? cnt + 1 : cnt);
			
			// 그 다음 초밥을 먹어볼까나
			start++;
			// 한바퀴 다 돈 상태면
			if(start == (N-1) + K) break;
		}
	}

}
