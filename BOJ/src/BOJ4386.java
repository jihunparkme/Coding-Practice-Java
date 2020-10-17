import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4386 {
	
	static int N;
	static Point[] stars;
	static ArrayList<Node>[] adj;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		stars = new Point[N];
		adj = new ArrayList[N];
		// 별 위치 저장.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			
			stars[i] = new Point(x, y);
			adj[i] = new ArrayList<>();
		}
		// 별들간의 거리 저장
		for (int i = 0; i < N; i++) {
			Point now = stars[i];
			
			for (int j = i + 1; j < N; j++) {
				Point next = stars[j];
				
				// Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))
				double dist = Math.sqrt(Math.pow(next.x - now.x, 2) 
						+ Math.pow(next.y - now.y, 2));
				
				adj[i].add(new Node(j, dist));
				adj[j].add(new Node(i, dist));
			}
		}
		
		System.out.printf("%.2f", Prim());
	}

	private static double Prim() {
		double res = 0.0;
		int cnt = 0;
		
		boolean visited[] = new boolean[N];
		PriorityQueue<Node>	pq = new PriorityQueue<>();
		// 0번 별부터 출발
		pq.add(new Node(0, 0));
		
		while(!pq.isEmpty()) {
			
			Node now = pq.poll();
			// 이미 방문한 별이라면 pass
			if(visited[now.idx]) continue;
			
			res += now.dist;
			visited[now.idx] = true;
			// 모든 별을 확인했다면 return
			if(++cnt == N) return res;
			
			for (Node next : adj[now.idx]) {
				// 이미 방문한 별이라면 pass
				if(visited[next.idx]) continue;
				
				pq.add(next);
			}
		}
		
		return res;
	}

	static class Point {
		double x, y;

		public Point(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

	static class Node implements Comparable<Node>{
		int idx;
		double dist;
		
		public Node(int idx, double dist) {
			this.idx = idx;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.dist, o.dist);
		}
	}
}