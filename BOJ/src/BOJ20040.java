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
			
			if(process(a, b)) {
				res = i;
				break;
			}
		}
		
		System.out.println(res);
	}

	private static boolean process(int a, int b) {
		
		// union을 시도했지만 사이클이 형성되면
		if(!union(a, b))
			return true;
		
		return false;
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

	private static int find(int n) {
		if(n == parents[n]) return n;
		return parents[n] = find(parents[n]);
	}

}
