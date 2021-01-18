import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4991 {

	static int W, H, cntDirtyArea, map[][], answer;
	static Point[] coordinates;
	static int distances[][];
	static boolean visited[][], isSelect[];
	static Queue<Point> q;
	static int[] dr = {0, 1, 0, -1}, dc = {-1, 0, 1, 0};
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
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
			cntDirtyArea = 1;
			answer = Integer.MAX_VALUE;
			
			for (int i = 0; i < H; i++) {
				char tmp[] = br.readLine().toCharArray();
				
				for (int j = 0; j < W; j++) {
					map[i][j] = tmp[j];
					
					if(map[i][j] == 'o') {
						coordinates[0] = new Point(i, j);
					} else if(map[i][j] == '*') {
						coordinates[cntDirtyArea++] = new Point(i, j);
					}
				}
			}
			
			isSelect = new boolean[cntDirtyArea];
			if(calculateDistances()) {
				cleaning(0, 0, 0);
				System.out.println(answer);
			} else {
				System.out.println(-1);
			}
		}
	}

	private static boolean calculateDistances() {
		for (int i = 0; i < cntDirtyArea; i++) {
			for (int j = i + 1; j < cntDirtyArea; j++) {
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

	private static void cleaning(int now, int cnt, int sum) {
		if(cnt == cntDirtyArea - 1) {
			answer = Math.min(answer, sum);
			return;
		}
		
		for (int i = 1; i < cntDirtyArea; i++) {
			if(isSelect[i]) continue;
			
			isSelect[i] = true;
			cleaning(i, cnt + 1, sum + distances[now][i]);
			isSelect[i] = false;
		}
	}
}