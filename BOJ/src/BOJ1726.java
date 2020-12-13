import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1726 {

	static int M, N, map[][];
	static int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0}; 
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 세로
		N = Integer.parseInt(st.nextToken()); // 가로
		map = new int[M][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Robot[] position = new Robot[2];
		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			
			position[i] = new Robot(a, b, c, 0); 
		}
		
		System.out.println(process(position[0], position[1]));
	}

	private static int process(Robot start, Robot end) {

		boolean[][][] visited = new boolean[4][M][N];
		Queue<Robot> q = new LinkedList<>();
		visited[start.dir][start.r][start.c] = true;
		q.add(new Robot(start.r, start.c, start.dir, start.cnt));
		
		while(!q.isEmpty()) {
			
			Robot now = q.poll();
			// 도착 지점에 도달
			if(now.r == end.r && now.c == end.c && now.dir == end.dir) return now.cnt;
			
			// 해당 방향으로 이동(최대 3 칸) 
			for (int i = 1; i <= 3; i++) {
				int rr = now.r + dr[now.dir] * i;
				int cc = now.c + dc[now.dir] * i;
				// 범위를 초과하거나 갈 수 없는 곳이라면
				if(rr < 0 || cc < 0 || rr >= M || cc >= N || map[rr][cc] == 1) break;
				// 해당 방향으로 이미 방문한 곳이라면
				if(visited[now.dir][rr][cc]) continue;
				
				visited[now.dir][rr][cc] = true;
				q.add(new Robot(rr, cc, now.dir, now.cnt + 1));
			}
			
			// 방향을 전환
			for (int d = 0; d < 4; d++) {
				// 같은 방향이거나 해당 방향으로 이미 방문한 곳
				if(d == now.dir || visited[d][now.r][now.c]) continue;
				
				if((d == 0 && now.dir == 1) || (d == 1 && now.dir == 0) ||
						(d == 2 && now.dir == 3) || (d == 3 && now.dir == 2)) {
					q.add(new Robot(now.r, now.c, d, now.cnt + 2));	
				} else {
					q.add(new Robot(now.r, now.c, d, now.cnt + 1));
				}
				
				visited[d][now.r][now.c] = true;
			}
		}
		
		return -1;
	}

	static class Robot {
		int r, c, dir, cnt;

		public Robot(int r, int c, int dir, int cnt) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.cnt = cnt;
		}
	}
}
