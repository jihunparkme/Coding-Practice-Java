import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3197 {

	static int R, C, cntIce;
	static char map[][];
	static Queue<Point> iceList[];
	static Point swans[];
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R + 2][C + 2];
		swans = new Point[2];
		
		// 사이드 채우기
		for (int c = 0; c <= C + 1; c++) {
			map[0][c] = '.';
			map[R + 1][c] = '.';
		}
		for (int r = 0; r <= R + 1; r++) {
			map[r][0] = '.';
			map[r][C + 1] = '.';
		}
		
		int idx = 0;
		// 두 마리의 백조의 위치
		for (int i = 1; i <= R; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 1; j <= C; j++) {
				map[i][j] = tmp[j - 1];
				// 백조일 경우
				if(map[i][j] == 'L') {
					swans[idx++] = new Point(i, j);
					map[i][j] = '.';
				} else if(map[i][j] == 'X') {
					// 빙판 공간일 경우
					cntIce++;
				}
			}
		}
		
		System.out.println(process());
	}

	
	private static int process() {
		
		int time = 0;
		boolean visited[][] = new boolean[R + 2][C + 2];
		Queue<Point> q[] = new LinkedList[2];
		q[0] = new LinkedList<>();
		q[1] = new LinkedList<>();
		q[0].add(new Point(0, 0));
		
		int cur = 0, nxt = 0;
		while(cntIce > 0) {
			++time;
			
			while(!q[cur].isEmpty()) {
				Point now = q[cur].poll();
				// 4방 탐색
				for (int d = 0; d < 4; d++) {
					int rr = now.r + dr[d];
					int cc = now.c + dc[d];
					// 범위를 초과하거나 이미 방문했을 경우
					if(rr < 1 || cc < 1 || rr > R || cc > C || visited[rr][cc]) continue;
					// 빙산일 경우
					if(map[rr][cc] == 'X') {
						// 물 공간과 접촉한 모든 빙판 공간이 녹는다.
						map[rr][cc] = '.';
						q[nxt].add(new Point(rr, cc));
						cntIce--;
					} else { // 물일 경우
						visited[rr][cc] = true;
						q[cur].add(new Point(rr, cc));
					}
				}
			}
			
			// 백조가 만날 수 있는지 확인
			
			// 만날 수 있다면 return
			
			cur ^= 1;
			nxt ^= 1;
		}
		
		return time;
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