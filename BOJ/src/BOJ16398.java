import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16398 {

	static int N;
	static ArrayList<Node> adj[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}
		// 행정 정보
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				int weight = Integer.parseInt(st.nextToken());
				if(i == j) continue;
				adj[i].add(new Node(j, weight));
			}
		}

		System.out.println(prim());
	}

	private static long prim() {
		
		long res = 0;
		int cnt = 0;
		boolean visited[] = new boolean[N];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		// 0번 행성부터 출발
		pq.add(new Node(0, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			// 이미 방문한 행성
			if(visited[now.to]) continue;
			
			res += now.w;
			visited[now.to] = true;
			if(++cnt == N) return res;
			
			for (Node next : adj[now.to]) {
				if(visited[next.to]) continue;
				pq.add(next);
			}
		}
		
		return res;
	}

	
	static class Node implements Comparable<Node> {
		int to, w;

		public Node(int to, int w) {
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
}
