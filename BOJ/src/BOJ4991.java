import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4991 {

	static int W, H, cntDirtyArea, map[][];
	static Point[] coordinates;
	static int distances[][], minCosts[];
	static boolean visited[][], visitedNode[];
	static PriorityQueue<Node> pq;
	static Queue<Point> q;
	static int[] dr = {0, 1, 0, -1}, dc = {-1, 0, 1, 0};
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static class Node implements Comparable<Node> {
		int to;
		int weight;
		public Node(int to, int weight) {
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
		
		while(true) {
			
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken()); // 방의 가로
			H = Integer.parseInt(st.nextToken()); // 방의 세로
			if(W == 0 && H == 0) break;
			
			map = new int[H][W];
			coordinates = new Point[11]; // 더러운 칸은 최대 10칸
			distances = new int[11][11];
			cntDirtyArea = 0;
			
			for (int i = 0; i < H; i++) {
				char tmp[] = br.readLine().toCharArray();
				
				for (int j = 0; j < W; j++) {
					map[i][j] = tmp[j];
					
					if(map[i][j] == 'o') {
						coordinates[0] = new Point(i, j);
					} else if(map[i][j] == '*') {
						coordinates[++cntDirtyArea] = new Point(i, j);
					}
				}
			}
			
			if(calculateDistances()) {
				System.out.println(cleaning());
			} else {
				System.out.println(-1);
			}
		}
	}

	private static boolean calculateDistances() {
		for (int i = 0; i <= cntDirtyArea; i++) {
			for (int j = i + 1; j <= cntDirtyArea; j++) {
				int d = dist(coordinates[i], coordinates[j]);
				if(d == -1) {
					return false;
				} else {
					distances[i][j] = distances[j][i] = d;
				}
			}
		}
		return true;
	}

	private static int dist(Point start, Point end) {
		
		q = new LinkedList<>();
		visited= new boolean[H][W];
		q.add(start);
		visited[start.r][start.c] = true;
		
		int result = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			++result;
			
			while(size-- > 0) {
				Point now = q.poll();
				for (int d = 0; d < 4; d++) {
					int rr = now.r + dr[d];
					int cc = now.c + dc[d];
					if(rr < 0 || cc < 0 || rr >= H || cc >= W || 
							visited[rr][cc] || map[rr][cc] == 'x') continue;
					if(rr == end.r && cc == end.c) return result;
					visited[rr][cc] = true;
					q.add(new Point(rr, cc));
				}
			}
		}
		return -1;
	}

	private static int cleaning() {
		
		pq = new PriorityQueue<Node>();
		visitedNode = new boolean[11];
		minCosts = new int[11];
		for (int i = 0; i <= cntDirtyArea; i++) {
			minCosts[i] = Integer.MAX_VALUE;
		}
		
		int result = 0, cntNode = 0;
		minCosts[0] = 0;
		pq.add(new Node(0, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(visitedNode[now.to]) continue;
			
			result += now.weight;
			System.out.println(now.to + " ::: " + now.weight);
			visitedNode[now.to]  = true;
			if(++cntNode == cntDirtyArea + 1) return result;
			
			for (int i = 0; i <= cntDirtyArea; i++) {
				if(!visitedNode[i] && distances[now.to][i] != 0 && distances[now.to][i] < minCosts[i]) {
					minCosts[i] = distances[now.to][i];
					pq.add(new Node(i, minCosts[i]));
				}
			}
		}
		
		// 청소할 수 없는 더러운 칸이 존재
		return -1;
	}
}


/*
1. 각 칸(초기 로봇 위치 + 더러운 칸)간의 거리 구하기 O(10^2)
2. 순열
*/