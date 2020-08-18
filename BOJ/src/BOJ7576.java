import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576 {

	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	static class Pnt {
		static int x;
		static int y;

		public Pnt(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];

		int cntZero = 0;
		Queue<Point> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) q.offer(new Point(i, j));
				if (map[i][j] == 0) cntZero++;
			}
		}
		// 모든 토마토가 익어있는 상태
		if(cntZero == 0) {
			System.out.println("0");
			return;
		}

		int res = -1;
		while (!q.isEmpty()) {
			// 현재 Queue Size를 미리 저장
			int tmpN = q.size();
			res++;
			for (int day = 0; day < tmpN; day++) {
				Point tmp = q.poll();
				// 인접한 토마토 찾기
				for (int i = 0; i < 4; i++) {
					int xx = tmp.x + dx[i];
					int yy = tmp.y + dy[i];
					// 범위를 벗어날 경우 pass
					if(xx < 0 || yy < 0 || xx >= N || yy>=M) continue;
					// 인접한 토마토가 익지 않았다면 익혀주고 Queue에 넣기
					if(map[xx][yy] == 0) {
						map[xx][yy] = 1;
						cntZero--;
						q.offer(new Point(xx, yy));
					}
				}
			}
		}
		// 아직 덜 익은 토마토가 있다면
		if(cntZero != 0) System.out.println("-1");
		else System.out.println(res);
	}
}
