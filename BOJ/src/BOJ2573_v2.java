import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573_v2 {
	
	static int N, M, map[][];
	static boolean[][] visited;
	static Queue<Point> q;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M]; // 빙산 높이 정보
		q = new LinkedList<>(); // 빙산이 있는 영역
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 빙산이 있을 경우
				if(map[i][j] > 0) q.add(new Point(i, j)); 
			}
		}
		
		System.out.println(process());
	}
	
	private static int process() {

		int time = 0, size = 0, cnt = 0;
		while(!q.isEmpty()) {
			
			++time;
			size = q.size();
			cnt = 0;
			visited = new boolean[N][M];
			
			while(size-- > 0) {
				Point now = q.poll();
				// 이미 방문한 영역은 pass
				if(visited[now.r][now.c]) continue;
				
				meltIce(now.r, now.c);
				cnt++;
			}

			// 빙산이 두 덩어리 이상으로 분리되었다면
			if( cnt >= 2) return time - 1;
		}
		// 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않을 경우
		return 0;
	}

	private static void meltIce(int r, int c) {
		
		visited[r][c] = true;
		// 4방 탐색
		for (int d = 0; d < 4; d++) {
			int rr = r + dr[d];
			int cc = c + dc[d];
			// 범위를 벗어나거나 이미 방문한 영역일 경우
			if(rr < 0 || rr >= N || cc < 0 || cc >= M || visited[rr][cc]) continue;
			// 바다일 경우
			if(map[rr][cc] == 0) {
				// 빙산이 녹는다...
				if(map[r][c] > 0) {
					map[r][c]--;					
				}
			} else {
				meltIce(rr, cc);
			}
		}
		// 빙산이 남아있다면
		if(map[r][c] > 0) q.add(new Point(r, c));
	}

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
