import java.io.*;
import java.util.*;

public class Main {

	FastScanner in;
	PrintWriter out;
	
	static int N, a, b, cntAdjacentLoads[];
	void solve() {
		N = in.nextInt();
		cntAdjacentLoads = new int[N + 1];
		
		for (int i = 0; i < N-1; i++) {
			a = in.nextInt();
			b = in.nextInt();
			cntAdjacentLoads[a]++;
			cntAdjacentLoads[b]++;
		}

		int day = 0;
		for (int i = 1; i <= N; i++) {
			if (cntAdjacentLoads[i] > 1) { // 연결된 길이 있을 경우 (자식 노드가 있을 경우)
				if (i != 1) cntAdjacentLoads[i]--; // 부모 노드는 이미 방문하였으므로 제외 (root 노드는 부모 노드가 없으므로 pass)
				int infectedCow = 1; // 감염된 소
				while (infectedCow < cntAdjacentLoads[i] + 1) { // 연결된 농장의 개수(+1은 현재 농장)만큼 감염된 소 증가
					infectedCow *= 2; // 해당 농장에서 감염 소 2배 증가
					++day;
				}
			} 

			if (i != 1) day++; // 감염된 소 한 마리가 인접 농장으로 이동하는 이벤트
		}
		
		out.println(day);
	}

	void run() {
		in = new FastScanner();
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	public static void main(String[] args) {
		new Main().run();
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		public FastScanner() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastScanner(String s) {
			try {
				br = new BufferedReader(new FileReader(s));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		String nextToken() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(nextToken());
		}

		long nextLong() {
			return Long.parseLong(nextToken());
		}

		double nextDouble() {
			return Double.parseDouble(nextToken());
		}
	}
}