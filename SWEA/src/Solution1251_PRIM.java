import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution1251_PRIM {

	public static class Node implements Comparable<Node> {
		int no;
		long w;

		public Node(int no, long w) {
			this.no = no;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.w, o.w);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			// 섬의 갯수
			int N = Integer.parseInt(br.readLine());
			Point[] land = new Point[N];
			boolean[] visited = new boolean[N];
			// 섬들의 x 좌표 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) { 
				land[i] = new Point(0, 0);
				land[i].x = Integer.parseInt(st.nextToken());
			}
			// 섬들의 y좌표 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) 
				land[i].y = Integer.parseInt(st.nextToken());
			// 세율
			double E = Double.parseDouble(br.readLine());
			
			// 모든 간선의 비용을 저장
			ArrayList<Node>[] adj = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				adj[i] = new ArrayList<>();
				
				for (int j = 0; j < N; j++) {
					if(i == j) continue;
					long distX = Math.abs(land[i].x - land[j].x);
					long distY = Math.abs(land[i].y - land[j].y); 
					adj[i].add(new Node(j, distX * distX + distY * distY));
				}
				
			}
			
			long res = 0;
			int nodeCnt = 0;
			PriorityQueue<Node> q = new PriorityQueue<>();
			q.add(new Node(0, 0));
		
			while(!q.isEmpty()) {
				Node now = q.poll();
				// 이미 확인한 노드는 pass
				if(visited[now.no]) continue;
				
				res += now.w;
				visited[now.no] = true;
				// 모든 노드 확인 완료
				if(++nodeCnt == N) break;
				
				for (int i = 0; i < adj[now.no].size(); i++) {
					Node next = adj[now.no].get(i);
					if(!visited[next.no]) {
						q.add(next);
					}
				}
			}
			
			// 총 환경 부담금을 최소로 지불하며, N개의 모든 섬을 연결할 수 있는 교통 시스템을 설계
			System.out.println("#" + tc + " " + Math.round(res * E));
		}
	}
}
