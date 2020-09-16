import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1926 {

	static int N, M, picture, res, map[][];
	static boolean visited[][];
	static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };

	static private int bfs(int x, int y) {

		int cnt = 1;
		Queue<Point> q = new LinkedList<>();
		visited[x][y] = true;
		q.add(new Point(x, y));

		while (!q.isEmpty()) {
			Point now = q.poll();

			for (int d = 0; d < 4; d++) {
				int xx = now.x + dx[d];
				int yy = now.y + dy[d];

				if (xx < 0 || yy < 0 || xx >= N || yy >= M) continue;
				if (visited[xx][yy] || map[xx][yy] == 0) continue;
				
				cnt++;
				visited[xx][yy] = true;
				map[xx][yy] = 0;
				
				q.add(new Point(xx, yy));
			}
		}

		return cnt;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					res = Math.max(res, bfs(i, j));
					picture++;
				}
			}
		}

		System.out.println(picture + "\n" + res);
	}

}
