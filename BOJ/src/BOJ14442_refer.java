import java.io.*;
import java.util.*;

/**
 * 
 * @reference sjr0115
 *
 */

public class BOJ14442_refer {
	static int N, M, K;
	static int[][] map;
	static int[][] visited;
	static int[] dy = { 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 0, 0 };

	public static class Point {
		// x좌표, y좌표, 벽을 부순 횟수
		int y;
		int x;
		int wall;

		public Point(int y, int x, int wall) {
			super();
			this.y = y;
			this.x = x;
			this.wall = wall;
		}

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			String data = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = data.charAt(j) - '0';
				// 벽을 부순 횟수를 저장하는 visited 배열을 최댓값으로 초기화
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		System.out.println(bfs());
	}

	public static int bfs() {
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(0, 0, 0));
		visited[0][0] = 0;
		int cnt = 0;
		
		while (!q.isEmpty()) {
			int size = q.size();
			cnt++;
			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				if(p.y == N - 1 && p.x == M - 1) {
					return cnt;
				}
				for (int d = 0; d < 4; d++) {
					int ny = p.y + dy[d];
					int nx = p.x + dx[d];
					if (ny < 0 || nx < 0 || ny >= N || nx >= M)
						continue;
					int nk = p.wall + map[ny][nx];
					if (nk > K || visited[ny][nx] <= nk)
						continue;
					visited[ny][nx] = nk;
					q.add(new Point(ny, nx, nk));
				}
			}
		}
		return -1;
	}
}