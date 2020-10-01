import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1922 {

	static int N, M;
	static ArrayList<Node>[] adj;
	
	static class Node implements Comparable<Node> {
		int to, weight;

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		// Edge 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// 인접 리스트
			adj[a].add(new Node(b, c));
			adj[b].add(new Node(a, c));
		}

		System.out.println(prim());
	}

	private static int prim() {
		
		boolean[] visited = new boolean[N + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		
		int res = 0, cnt = 0;
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			// 이미 방문한 정점
			if(visited[now.to]) continue;
			
			res += now.weight;
			visited[now.to] = true;
			// 모든 정점 방문
			if(++cnt == N) return res;
			
			for (Node next : adj[now.to]) {
				// 이미 방문했을 경우
				if(visited[next.to]) continue;
				
				pq.add(next);
			}
		}
	
		return res;
	}

}
