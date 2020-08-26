import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206_v2 {

	static int N, M, res = -1, map[][], visited[][];
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	static class pos {
		int x, y, d, k;

		public pos(int x, int y, int d, int k) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.k = k;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		map = new int[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}

		Queue<pos> q = new LinkedList<>();
		// 시작 칸과 끝나는 칸도 포함
		q.add(new pos(0, 0, 1, 0));

		while (!q.isEmpty()) {
			pos now = q.poll();
			if (now.x == N - 1 && now.y == M - 1) {
				res = now.d;
				break;
			}

			for (int d = 0; d < 4; d++) {
				int xx = now.x + dx[d];
				int yy = now.y + dy[d];

				// 범위를 벗어날 경우
				if (xx < 0 || yy < 0 || xx >= N || yy >= M)
					continue;
				// 이동할 수 있는 곳으로 이동
				if (map[xx][yy] == 0) {
					// 이미 왔던 길일 경우
					if (visited[xx][yy] == 1 || visited[xx][yy] == 3)
						continue;
					q.add(new pos(xx, yy, now.d + 1, now.k));
					if (visited[xx][yy] == 0) visited[xx][yy] = 1;
					if (visited[xx][yy] == 2) visited[xx][yy] = 3;
				} else {
					// 벽을 부술 수 있고, 벽으로 이동해야할 경우
					// 벽을 부수고 이미 왔던 길일 경우
					if (now.k != 0 || visited[xx][yy] >= 2)
						continue;
					q.add(new pos(xx, yy, now.d + 1, now.k + 1));
					if (visited[xx][yy] == 0) visited[xx][yy] = 2;
					if (visited[xx][yy] == 1) visited[xx][yy] = 3;
				}
			}
		}

		System.out.println(res);
	}
}
