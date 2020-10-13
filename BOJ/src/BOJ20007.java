import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ20007 {

	static int N, M, X, Y, INF = Integer.MAX_VALUE;
	static Edge totalDist[];
	static ArrayList<Edge>[] adj;
	static class Edge implements Comparable<Edge> {
		int to, dist;
		
		public Edge(int to, int dist) {
			super();
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.dist, o.dist);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 집 개수
		M = Integer.parseInt(st.nextToken()); // 도로 개수
		X = Integer.parseInt(st.nextToken()); // 최대 갈 수 있는 거리
		Y = Integer.parseInt(st.nextToken()); // 성현이 집
		
		totalDist = new Edge[N];		
		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			totalDist[i] = new Edge(i, 0);
			adj[i] = new ArrayList<>();
		}
		
		// 도로 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Edge(b, w));
			adj[b].add(new Edge(a, w));
		}
		
		System.out.println(process());
	}

	private static int process() {
		
		// 성현이 집으로부터 각 집까지의 최단 거리
		dijkstra();
		
		// 최단 거리가 X보다 클 경우(방문할 수 없는 집)
		for (int i = 0; i < N; i++) {
			if(totalDist[i].to > X) return -1;
		}
		
		Arrays.sort(totalDist);
		// 가까운 집부터 방문
		int day = 0;
		int idx = -1;
		int tmp = 0;
		while(idx < N) {
			if(++idx == Y) continue;
			
			if(tmp + totalDist[idx].dist > X) 
		}
		
		System.out.println(Arrays.toString(totalDist));
				
		return 0;
	}

	private static void dijkstra() {
		
		for (int i = 0; i < N; i++) {
			totalDist[i].dist = INF;		
		}
		totalDist[Y] = new Edge(Y, 0);
		
		// 성현이 집에서부터 출발
		boolean[] visited = new boolean[N];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(Y, 0));
		
		while(!pq.isEmpty()) {
			
			Edge now = pq.poll();
			// 이미 방문한 집이면 pass
			if(visited[now.to]) continue;
		
			for (Edge next : adj[now.to]) {
				if(!visited[next.to] && totalDist[next.to].dist > totalDist[now.to].dist + next.dist) {
					totalDist[next.to].dist = totalDist[now.to].dist + next.dist;
					pq.add(new Edge(next.to, totalDist[next.to].dist));
				}
			}
			
			visited[now.to] = true;
		}
		
	}

}
