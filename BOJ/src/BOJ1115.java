import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1115 {

	static int N, res = 987654321;
	static int[] P, A, B;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N];
		P = new int[N];
		A = new int[N];
		B = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) 
			P[i] = Integer.parseInt(st.nextToken());
				
		go(0);
		System.out.println(res);
	}

	private static void go(int cnt) {
		
		// 순열 완성
		if(cnt == N) {
			// 완벽한 수열일 경우 차이를 저장
			if(isPerfect()) {
				int tmp = 0;
				for (int i = 0; i < N; i++) 
					if(P[i] != A[i]) tmp++;
								
				res = Math.min(res, tmp);
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			A[cnt] = i;
			go(cnt + 1);
			visited[i] = false;
		}
		
	}

	private static boolean isPerfect() {
		
		boolean[] isUsed = new boolean[N];
		B = new int[N];
		
		B[0] = 0;
		isUsed[0] = true;
		// 만들어진 순열의 자식 배열 생성
		for (int i = 1; i < N; i++) {
			B[i] = A[B[i-1]];
			isUsed[B[i]] = true;
		}
		
		// 자식 배열이 순열인지 확인
		for (int i = 0; i < N; i++) {
			// 사용이 안된 숫자가 있다면 순열이 아님
			if(!isUsed[i]) return false;
		}
		
		return true;
	}

}
