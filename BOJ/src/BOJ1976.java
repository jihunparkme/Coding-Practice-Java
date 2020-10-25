import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976 {

	static int N, M, parents[], plan[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		plan = new int[M];
		parents = new int[N];
		// Set
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		// 도시 정보
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				// 두 도시를 연결
				if(tmp == 1) union(i, j);
			}
		}
		// 여행 계획
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		boolean isSuccess = true;
		for (int i = 0; i < M - 1; i++) {
			// 두 도시가 연결되어있지 않다면
			if(find(plan[i]) != find(plan[i + 1])) {
				isSuccess = false;
				break;
			}
		}
		
		if(isSuccess) System.out.println("YES");
		else System.out.println("NO");
	}

	private static boolean union(int a, int b) {
		
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		
		return true;
		
	}

	private static int find(int a) {
		if(a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
}