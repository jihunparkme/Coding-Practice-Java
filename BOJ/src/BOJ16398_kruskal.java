import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16398_kruskal {

	static int N, parents[];
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		parents = new int[N];
		pq = new PriorityQueue<>();
		// 행정 정보
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				int weight = Integer.parseInt(st.nextToken());
				if(i < j) pq.add(new Edge(i, j, weight));
			}
		}

		System.out.println(prim());
	}

	private static long prim() {
		
		long res = 0;
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			if(union(now)) {
				res += now.w;
				if(++cnt == N - 1) return res;
			}
		}
		
		return res;
	}

	private static boolean union(Edge now) {
		
		int aRoot = find(now.from);
		int bRoot = find(now.to);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
		
	}

	private static int find(int n) {
		
		if(n == parents[n]) return n;
		return parents[n] = find(parents[n]);
		
	}

	static class Edge implements Comparable<Edge> {
		int from, to, w;

		public Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
}
