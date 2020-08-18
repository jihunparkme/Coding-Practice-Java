import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576_Class {

	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	static public class Pnt {
		int x;
		int y;

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
		Queue<Pnt> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 익은 토마토의 위치를 Queue에 add
				if (map[i][j] == 1) q.add(new Pnt(i, j));
				// 익지 않은 토마토 count
				if (map[i][j] == 0) cntZero++;
			}
		}
		// 모든 토마토가 익어있는 상태라면
		if (cntZero == 0) {
			System.out.println("0");
			return;
		}

		int res = -1;
		// 익은 토마토의 위치를 Queue에서 꺼내면서 주변 토마토를 익혀보자
		while (!q.isEmpty()) {
			// 현재 Queue Size를 미리 저장
			int tmpN = q.size();
			res++;
			for (int day = 0; day < tmpN; day++) {
				Pnt tmpPnt = q.poll();
				// 인접한 안 익은 토마토 찾기
				for (int i = 0; i < 4; i++) {
					int xx = tmpPnt.x + dx[i];
					int yy = tmpPnt.y + dy[i];
					// 범위를 벗어날 경우 pass
					if (xx < 0 || yy < 0 || xx >= N || yy >= M) continue;
					// 인접한 토마토가 익지 않았다면 익혀주고 Queue에 넣기
					if (map[xx][yy] == 0) {
						map[xx][yy] = 1;
						cntZero--;
						q.add(new Pnt(xx, yy));
					}
				}
			}
		}
		// 아직 덜 익은 토마토가 있다면
		if (cntZero != 0)
			System.out.println("-1");
		else
			System.out.println(res);
	}
}
