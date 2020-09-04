import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ17472 {

	static int R, C, map[][];
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static ArrayList<Edge> edgeList;
	static class Edge implements Comparable<Edge> {
		int from, to, dist;
		
		public Edge(int from, int to, int dist) {
			this.from = from;
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
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		// 먼저 각 섬들에게 각기 다른 번호를 부여(2번부터 부여)
		int num = 2;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 육지라면
				if(map[i][j] == 1) {
					map[i][j] = num;
					setLandName(i, j, num++);
				}
			}
		}
		
		edgeList = new ArrayList<>();
		// 각 섬들로부터 다리를 설치하는데, 다른 섬이 나오면 그 섬까지의 거리를 우선순위 큐에 넣기
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 섬이 아니면 pass
				if(!(map[i][j] >= 2)) continue;
				
				// 섬이면 4방으로 쭉 다리를 놓아보자.
				for (int d = 0; d < 4; d++) {
					setBridge(i, j, d, map[i][j]);
				}
			}
		}
		
		
		System.out.println(connectLand(num));
	}

	static int[] parents;
	private static int connectLand(int num) {

		edgeList.sort(null);
		parents = new int[num];
		
		// Set
		for (int i = 2; i < num; i++) 
			parents[i] = i;
		
		int cnt = 0, res = 0;
		for (Edge edge : edgeList) {
			// 싸이클 형성이 안된다면
			if(union(edge.from, edge.to)) {
				res += edge.dist;
				// 섬을 모두 연결
				if(++cnt == (num - 2) - 1) return res;
			}
		}
		
		return -1;
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

	private static void setBridge(int r, int c, int d, int here) {
		
		int len = 0;
		
		while(true) {
			r += dr[d];
			c += dc[d];
			// 범위를 벗어나면 out
			if(r < 0 || c < 0 || r >= R || c >= C) return;
			// 같은 섬이면 out
			if(map[r][c] == here) return;
			len++;
			// 다른 섬을 만났는데 길이가 2 이상이라면
			if(map[r][c] != here && map[r][c] >= 2) {
				if(len - 1 >= 2) {
					// 그 섬까지의 거리를 우선순위 큐에 넣기
					edgeList.add(new Edge(here, map[r][c], len - 1));
				}
				return;
			}
			
		}
	}

	private static void setLandName(int r, int c, int num) {
		
		// 4방 탐색
		for (int d = 0; d < 4; d++) {
			int rr = r + dr[d];
			int cc = c + dc[d];
			// 범위를 벗어나면 pass
			if(rr < 0 || cc < 0 || rr >= R || cc >= C) continue;
			// 육지가 아닐 경우
			if(map[rr][cc] != 1) continue;
			
			map[rr][cc] = num;
			setLandName(rr, cc, num);
		}
		
	}

}
