import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3197 {

	static int R, C, cur, nxt;
	static char map[][];
	static boolean[][] visited, vsitSwan;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static Queue<Point>[] q;
	static Queue<Point> qSwan;
	static Point swans[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		swans = new Point[2];
		
		q = new LinkedList[2];
		q[0] = new LinkedList<>();
		q[1] = new LinkedList<>();
		cur = 0; nxt = 1;
		
		int idx = 0;
		// 두 마리의 백조의 위치
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				// 백조일 경우
				if(map[i][j] == 'L') {
					swans[idx++] = new Point(i, j);
					map[i][j] = '.';
				} else if(map[i][j] == '.') {
					q[cur].add(new Point(i, j));
				}
			}
		}
		
		System.out.println(process());
	}

	
	private static int process() {
		
		int time = 0;
		visited = new boolean[R][C];
		
		while(true) {
			++time;
			
			melt();
//			printMap();
			
			// 백조가 만날 수 있는지 확인
			if(isMeet()) 
				return time;
			
			cur ^= 1;
			nxt ^= 1;
		}
	}

	private static boolean isMeet() {
		
		vsitSwan = new boolean[R][C];
		qSwan = new LinkedList<>();
		qSwan.add(swans[0]);
		vsitSwan[swans[0].r][swans[0].c] = true;
		
		for (int s = 0; s < 2; s++) {
			while(!qSwan.isEmpty()) {
				Point now = qSwan.poll();
				
				// 4방 탐색
				for (int d = 0; d < 4; d++) {
					int rr = now.r + dr[d];
					int cc = now.c + dc[d];
					// 범위를 초과할 경우
					if(rr < 0 || cc < 0 || rr >= R || cc >= C) continue;
					// 이미 방문했거나 빙산일 경우
					if(vsitSwan[rr][cc] || map[rr][cc] == 'X') continue;
					// 상대 백조를 만났을 경우
					if(rr == swans[1].r && cc == swans[1].c) return true;
					
					vsitSwan[rr][cc] = true;
					qSwan.add(new Point(rr, cc));
				}
			}
		}
		
		return false;
	}


	private static void melt() {
		
		while(!q[cur].isEmpty()) {
			Point now = q[cur].poll();
			visited[now.r][now.c] = true;
			
			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int rr = now.r + dr[d];
				int cc = now.c + dc[d];
				// 범위를 초과하거나 이미 방문했을 경우
				if(rr < 0 || cc < 0 || rr >= R || cc >= C || visited[rr][cc]) continue;
				
				visited[rr][cc] = true;
				
				// 빙산일 경우
				if(map[rr][cc] == 'X') {
					// 물 공간과 접촉한 모든 빙판 공간이 녹는다.
					map[rr][cc] = '.';
					q[nxt].add(new Point(rr, cc));
				} else { // 물일 경우
					q[cur].add(new Point(rr, cc));
				}
			}
		}
		
	}
	
	private static void printMap() {
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}System.out.println();
		}
		System.out.println();
	}

	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
}

/*
빙산, 치즈와 유사한 문제


*/