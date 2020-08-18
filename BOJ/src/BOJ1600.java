import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1600 {

	static int K, W, H, map[][];
	static int[][][] dist;
	static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };
	static int[] hx = {-1, -2, -1, -2, 1, 2, 1, 2}, hy = {-2, -1, 2, 1, -2, -1, 2, 1};

	static class Info {
		int x, y, k, cnt;

		public Info(int x, int y, int k, int cnt) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.cnt = cnt;
		}		
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine()); // 말처럼 이동할 수 있는 횟수
		st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken()); // 가로
		H = Integer.parseInt(st.nextToken()); // 세로
		map = new int[H][W];
		dist = new int[H][W][K+1];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int res = -1;
		Queue<Info> q = new LinkedList<>();
		// (0,0)에서 시작.
		q.add(new Info(0,0,0,0));
		dist[0][0][0] = 0;
		while(!q.isEmpty()) {
			Info now = q.poll();
			// 도착지에 도착했다면 
			if(now.x == H-1 && now.y == W-1) {
				res = dist[now.x][now.y][now.k];
				break;
			}
			
			// 총 K번만 말처럼 움직이고, 그 외에는 그냥 인접한 칸(대각선 제외)으로만 이동
			// 말처럼 이동할 수 있는 횟수가 남았다면
			if(now.k < K) {
				// 말처럼 이동해보자.
				for (int h = 0; h < 8; h++) {
					int xx = now.x + hx[h];
					int yy = now.y + hy[h];
					if(!shallGo(xx, yy, now.k + 1)) continue;
					// 이동할 수 있는 칸이면
					dist[xx][yy][now.k + 1 ] = now.cnt + 1;
					q.add(new Info(xx, yy, now.k + 1, now.cnt + 1));
				}
			}
			// 인접한 칸으로 이동
			for (int d = 0; d < 4; d++) {
				int xx = now.x + dx[d];
				int yy = now.y + dy[d];
				if(!shallGo(xx, yy, now.k)) continue;
				// 이동할 수 있는 칸이면
				dist[xx][yy][now.k] = now.cnt + 1;
				q.add(new Info(xx, yy, now.k, now.cnt + 1));
			}
		
		}
		System.out.println(res);
	}
	
	public static boolean shallGo(int x, int y, int k) {
		// 범위를 벗어나면 pass
		if(x < 0 || y < 0 || x >= H || y >= W) return false;
		// 벽이면 pass
		if(map[x][y] == 1) return false;
		// 이미 방문한 곳이면 pass
		if(dist[x][y][k] != 0) return false;
		
		return true;
	}
}