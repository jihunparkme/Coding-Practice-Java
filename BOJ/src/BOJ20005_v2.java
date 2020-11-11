import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20005_v2 {

	static int M, N, P, power[];
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, -1, 0, 1 };
	static char map[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 세로
		N = Integer.parseInt(st.nextToken()); // 가로
		P = Integer.parseInt(st.nextToken()); // 플레이어 수
		map = new char[M][N];
		power = new int[P]; // player의 dps 정보

		// Set map
		int bossR = 0, bossC = 0;
		for (int i = 0; i < M; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j);
				// Boss의 위치
				if (map[i][j] == 'B') {
					bossR = i;
					bossC = j;
				}
			}
		}

		// Set player power
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			char a = st.nextToken().charAt(0);
			int b = Integer.parseInt(st.nextToken());
			power[a - 'a'] = b;
		}

		int bossHp = Integer.parseInt(br.readLine());

		System.out.println(process(bossR, bossC, bossHp));
	}

	private static int process(int R, int C, int HP) {

		// 전리품을 받을 플레이어
		boolean[] selected = new boolean[P];
		boolean[][] visited = new boolean[M][N];
		int cnt = 0;
		// 보스의 출발점
		Queue<Boss> q = new LinkedList<>();
		q.add(new Boss(R, C));
		visited[R][C] = true;

		while (HP > 0) {

			int size = q.size();
			while (size-- > 0) {

				Boss now = q.poll();
				// 플레이어의 위치에 도달했을 경우
				if (map[now.r][now.c] >= 'a' && map[now.r][now.c] <= 'z') {
					// 해당 플레이어를 전리품 수령 대상에 포함
					selected[map[now.r][now.c] - 'a'] = true;
					cnt++;
				}
				// 보스의 이동
				for (int d = 0; d < 4; d++) {
					int rr = now.r + dr[d];
					int cc = now.c + dc[d];
					// 범위를 벗어날 경우
					if (rr < 0 || cc < 0 || rr >= M || cc >= N) continue;
					// 이미 방문했거나 이동할 수 없는 경우
					if (visited[rr][cc] || map[rr][cc] == 'X') continue;

					visited[rr][cc] = true;
					q.add(new Boss(rr, cc));
				}
			}

			// 다같이 보스 공격!
			for (int i = 0; i < P; i++) {
				if (selected[i]) HP -= power[i];
			}
		}

		return cnt;
	}

	static class Boss {
		int r, c;

		public Boss(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}
