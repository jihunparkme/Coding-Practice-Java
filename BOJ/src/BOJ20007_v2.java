import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * Fail : 잘못도니 접근..
 */

public class BOJ20007_v2 {

	static int N, M, X, Y, cnt, distInfo[][];
	static boolean[] visited;
	static ArrayList<Info>[] adj;
	static class Info implements Comparable<Info>{
		int to, totalDist, dist;
		
		public Info(int to, int totalDist, int dist) {
			super();
			this.to = to;
			this.totalDist = totalDist;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Info o) {
			return Integer.compare(this.totalDist, o.totalDist);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 집 개수
		M = Integer.parseInt(st.nextToken()); // 도로 개수
		X = Integer.parseInt(st.nextToken()); // 최대 갈 수 있는 거리
		Y = Integer.parseInt(st.nextToken()); // 성현이 집
		distInfo = new int[N][N];
		visited = new boolean[N];
		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) 
			adj[i] = new ArrayList<>();
		
		// 도로 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Info(b, w, 0));
			adj[b].add(new Info(a, w, 0));
			
			distInfo[a][b] = w;
			distInfo[b][a] = w;
		}
		
		System.out.println(go());
	}

	private static int go() {
		
		int res = operate();
		
		if(res == -1) return -1;
		else {
			res %= X;
			return res == 0 ? res : res + 1;
		}
	}

	private static int operate() {
		
		// 성현이 집에서부터 출발
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.add(new Info(Y, 0, 0));
		int walk = 0;
		
		// 가장 가까운 집부터 방문
		while(!pq.isEmpty()) {
			
			Info now = pq.poll();
			// 이미 방문한 집이면 pass
			if(visited[now.to]) continue;
			// 방문 완료!
			visited[now.to] = true;
			walk += now.dist * 2;
			// 모든 집을 다 돌았다면
			if(++cnt == N) {
				return walk;
			}
			
			for (int i = 0; i < adj[now.to].size(); i++) {
				Info tmp = adj[now.to].get(i);
				// 이미 방문한 집이면 pass
				if(visited[tmp.to]) continue;
				if((now.totalDist % 2) + tmp.totalDist * 2 > X) continue;
				
				// 떡 돌리러 가자~
				pq.add(new Info(tmp.to, now.totalDist + tmp.totalDist * 2, distInfo[now.to][tmp.to]));
			}
		}
		
		if(walk == 0 || cnt < N) return -1;
		else return walk;
		
	}

}
