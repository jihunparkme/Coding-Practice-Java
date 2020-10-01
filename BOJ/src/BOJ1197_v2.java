import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1197_v2 {

	static int V, E, parents[];
	static Edge[] edgeList;
	
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());	// 정점 개수
		E = Integer.parseInt(st.nextToken());	// 간선 개수
		
		edgeList = new Edge[E];
		parents = new int[V + 1];

		// Edge 정보 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// 인접 리스트
			edgeList[i] = new Edge(a, b, c);
		}
		
		System.out.println(kruskal());
	}

	private static int kruskal() {
		
		int res = 0, cnt = 0;
		
		// 간선 가중치 기준 오름차순 정렬
		Arrays.sort(edgeList);
		// 정점 초기화
		make();
		
		// 주어진 간선을 이어보면서
		for (Edge edge : edgeList) {
			// 싸이클이 형성되지 않는다면
			if(union(edge.from, edge.to)) {
				// 해당 간선을 사용
				res += edge.weight;
				// 정점 - 1 개의 간선이 이어졌다면 MST 완성!
				if(++cnt == V - 1) return res;
			}
		}
		
		return res;
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		parents[aRoot] = bRoot;
		return true;
	}

	private static int find(int a) {
		if(a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}

	private static void make() {
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}

}
