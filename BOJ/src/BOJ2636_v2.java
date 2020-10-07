import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636_v2 {
	
	static int R, C, cheeze, time, prevCnt;
	static boolean[][] map;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				// 초기 치즈 칸 count
				if (map[i][j] = (st.nextToken().charAt(0) - '0' == 1)) {
					cheeze++;
				}
			}
		}

		go();
		
		System.out.println(time);
		System.out.println(prevCnt);
	}

	private static void go() {
		
		int cnt = 0;
		boolean[][] visited = new boolean[R][C];
		// 공기와 닿은 치즈의 좌표를 저장하는 queue[curr]
		// 공기의 좌표를 저장하는 queue[next]
		LinkedList<Point>[] q = new LinkedList[2];
		q[0] = new LinkedList<>();
		q[1] = new LinkedList<>();

		q[0].offer(new Point(0, 0));
		int curr = 0, next = 1;
		while (cheeze > 0) {
			while (!q[curr].isEmpty()) {
				Point now = q[curr].poll();
				// 4방 탐색
				for (int d = 0; d < 4; d++) {
					int rr = now.x + dr[d];
					int cc = now.y + dc[d];
					// 범위를 벗어남
					if(rr < 0 || rr >= R || cc < 0 || cc >= C) continue;
					// 이미 방문
					if(visited[rr][cc]) continue;
					
					visited[rr][cc] = true;
					// 치즈일 경우
					if(map[rr][cc]) {
						// 녹여주고
						map[rr][cc] = false;
						// 치즈의 테두리를 저장
						q[next].add(new Point(rr, cc));
						cheeze--;
					} else { // 공기일 경우
						q[curr].add(new Point(rr, cc));
					}
				}
			}
			curr ^= 1;
			next ^= 1;
			cnt++;
		}

		time = cnt;
		prevCnt = q[curr].size();
	}
}