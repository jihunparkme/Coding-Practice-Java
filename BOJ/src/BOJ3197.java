import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3197 {

	static int R, C, idx, cur = 0, nxt = 1;
	static char map[][];
	static boolean[][] vstdIn, vstdOut;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static Queue<Point>[] water, swan;
	static Point swans[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); // 행 크기
		C = Integer.parseInt(st.nextToken()); // 열 크기
		map = new char[R][C];
		swans = new Point[2]; // 두 백조의 위치 정보
		// 물과 빙판 위치를 저장하는 Queue
		water = new LinkedList[2];
		water[0] = new LinkedList<>();
		water[1] = new LinkedList<>();
		// 백조가 현재와 다음 날 탐색할 위치를 저장하는 Queue 
		swan = new LinkedList[2];
		swan[0] = new LinkedList<>();
		swan[1] = new LinkedList<>();
	
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				// 두 마리의 백조 위치 저장
				if(map[i][j] == 'L') {
					swans[idx++] = new Point(i, j);
					map[i][j] = '.';
				} 
				// 물의 위치를 저장
				if(map[i][j] == '.') {
					water[cur].add(new Point(i, j));
				}
			}
		}
		
		System.out.println(process());
	}

	private static int process() {
		
		int time = 0;
		vstdIn = new boolean[R][C];
		vstdOut = new boolean[R][C];
		// 0번 백조가 1번 백조를 향해
		swan[cur].add(swans[0]);
		vstdIn[swans[0].r][swans[0].c] = true;
		
		while(true) {
			// 두 마리의 백조가 만날 수 있는지 확인
			if(isMeet()) return time;
			// 빙하가 녹는다
			melt();
			// index switch
			cur ^= 1;
			nxt ^= 1;
			
			time++;
		}
	}

	private static boolean isMeet() {
		// 현재 백조가 갈 수 있는 구간을 모두 탐색
		while(!swan[cur].isEmpty()) {
			Point now = swan[cur].poll();
			
			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int rr = now.r + dr[d];
				int cc = now.c + dc[d];
				// 범위를 초과하거나 이미 방문했을 경우 pass
				if(rr < 0 || cc < 0 || rr >= R || cc >= C || vstdIn[rr][cc]) continue;
				// 상대 백조를 만났을 경우 return
				if(rr == swans[1].r && cc == swans[1].c) return true;
				// 방문 처리
				vstdIn[rr][cc] = true;
				// 빙하일 경우 다음 날 탐색을 위해 Queue에 추가
				if(map[rr][cc] == 'X') {
					swan[nxt].add(new Point(rr, cc));
					map[rr][cc] = '.';
				} else {
					swan[cur].add(new Point(rr, cc));
				}
			}
		}
		
		return false;
	}

	private static void melt() {
		// 물이 있는 구간을 모두 탐색
		while(!water[cur].isEmpty()) {
			Point now = water[cur].poll();
			
			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int rr = now.r + dr[d];
				int cc = now.c + dc[d];
				// 범위를 초과하거나 이미 방문했을 경우
				if(rr < 0 || cc < 0 || rr >= R || cc >= C || vstdIn[rr][cc] || vstdOut[rr][cc]) continue;
				vstdOut[rr][cc] = true;
				// 빙산일 경우
				if(map[rr][cc] == 'X') {
					// 물 공간과 접촉한 모든 빙판 공간이 녹는다.
					map[rr][cc] = '.';
					water[nxt].add(new Point(rr, cc));
				} 
			}
		}
	}

	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
}