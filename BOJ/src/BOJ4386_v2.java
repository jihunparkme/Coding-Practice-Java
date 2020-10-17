import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ4386_v2 {
	
	static int N, parents[];
	static Point[] stars;
	static ArrayList<Edge> edgeList;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		stars = new Point[N];
		edgeList = new ArrayList<>();
		// 별 위치 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			
			stars[i] = new Point(x, y);
		}
		// 별들간의 거리 저장
		for (int i = 0; i < N; i++) {
			Point now = stars[i];
			
			for (int j = i + 1; j < N; j++) {
				Point next = stars[j];
				
				// Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))
				double dist = Math.sqrt(Math.pow(next.x - now.x, 2) 
						+ Math.pow(next.y - now.y, 2));
				
				edgeList.add(new Edge(i, j, dist));
			}
		}
		
		System.out.printf("%.2f", Prim());
	}

	private static double Prim() {
				
		edgeList.sort(null);
		
		parents = new int[N];
		make();
		
		double res = 0.0;
		int cnt = 0;
		for (Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				res += edge.dist;
				if(++cnt == N - 1) return res;
			}
		}
		
		return res;
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

	private static void make() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
	}

	static class Point {
		double x, y;

		public Point(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

	static class Edge implements Comparable<Edge>{
		int from, to;
		double dist;
		
		public Edge(int from, int to, double dist) {
			super();
			this.from = from;
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.dist, o.dist);
		}
	}

}
