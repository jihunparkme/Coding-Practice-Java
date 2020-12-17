import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14503 {

	static int N, M, map[][];
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1}; // 북서남동
	static Queue<Robot> q;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		// 북동남서
		if(c == 1) c = 3;
		else if(c == 3) c = 1;
		Robot start = new Robot(a, b, c);
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(process(start));
	}

	private static int process(Robot start) {
		
		int cnt = 0;
		q = new LinkedList<>();
		q.add(start);
		
		while(true) {
			
			Robot now = q.poll();
			// 1. 현재 위치를 청소
			if(map[now.r][now.c] == 0) {
				// 청소한 곳은 2로 체크
				map[now.r][now.c] = 2;
				cnt++;
			}
			
			// 2. 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색
			if(move(now)) continue;
			// 작동을 멈출 경우
			else return cnt;
		}
	}

	private static boolean move(Robot now) {
		
		int ndir = 0, rr = 0, cc = 0;
		for (int d = 1; d <= 4; d++) {
			ndir = (now.dir + d) % 4;
			rr = now.r + dr[ndir];
			cc = now.c + dc[ndir];
			// 이미 청소했거나 벽일 경우
			if(map[rr][cc] > 0) continue;

			// 아직 청소하지 않은 공간이 존재한다면, 
			if(map[rr][cc] == 0) {
				// 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행
				q.add(new Robot(rr, cc, ndir));
				return true;
			}
		}
	
		// 네 방향 모두 청소가 이미 되어있거나 벽인 경우
		// 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아감
		rr = now.r - dr[now.dir];
		cc = now.c - dc[now.dir];
		// 하지만, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다
		if(map[rr][cc] == 1) return false;
		else if(move(new Robot(rr, cc, now.dir))) return true;
		
		return false;
	}

	static class Robot {
		int r, c, dir;

		public Robot(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
}