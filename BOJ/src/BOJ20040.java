import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20040 {

	static int N, M, res, parents[];
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 사이클이 형성되는지 확인
			if(!union(a, b)) {
				res = i;
				break;
			}
		}
		
		System.out.println(res);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a); // a의 root 노드
		int bRoot = find(b); // b의 root 노드
		// a와 b의 root 노드가 같다면 사이클 형성
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

	private static int find(int n) {
		if(n == parents[n]) return n;
		return parents[n] = find(parents[n]);
	}

}
