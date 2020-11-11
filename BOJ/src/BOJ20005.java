import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20005 {

	static int M, N, P, power[], bossHp;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, -1, 0, 1 };
	static char map[][];
	static boolean[][][] visited;
	static Queue<Player> q;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 세로
		N = Integer.parseInt(st.nextToken()); // 가로
		P = Integer.parseInt(st.nextToken()); // 플레이어 수
		map = new char[M][N];
		power = new int[P]; // player의 dps 정보
		visited = new boolean[P][M][N];

		// Set map
		q = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j);
				// player의 위치가 등록된다면
				if (map[i][j] >= 'a' && map[i][j] <= 'z') {
					q.add(new Player(map[i][j] - 'a', i, j));
					visited[map[i][j] - 'a'][i][j] = true;
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

		bossHp = Integer.parseInt(br.readLine());

		System.out.println(process());
	}

	private static int process() {

		// 전리품을 받은 플레이어
		boolean[] selected = new boolean[P];
		int cnt = 0;

		while (bossHp > 0) {

			int size = q.size();
			while (size-- > 0) {

				Player now = q.poll();
				// 이미 전리품 수령이 확정된 플레이어일 경우 pass
				if (selected[now.id]) continue;
				// 보스의 위치에 도달했을 경우
				if (map[now.r][now.c] == 'B') {
					selected[now.id] = true;
					cnt++;
				}
				// 보스와 다른 위치에 있을 경우 이동
				for (int d = 0; d < 4; d++) {
					int rr = now.r + dr[d];
					int cc = now.c + dc[d];
					// 범위를 벗어날 경우
					if (rr < 0 || cc < 0 || rr >= M || cc >= N) continue;
					// 이미 방문했거나 이동할 수 없는 경우
					if (visited[now.id][rr][cc] || map[rr][cc] == 'X') continue;

					visited[now.id][rr][cc] = true;
					q.add(new Player(now.id, rr, cc));
				}
			}

			// 다같이 보스 공격!
			for (int i = 0; i < P; i++) {
				if(selected[i]) bossHp -= power[i];
			}
		}
		
		return cnt;
	}

	static class Player {
		int id, r, c;

		public Player(int id, int r, int c) {
			this.id = id;
			this.r = r;
			this.c = c;
		}

	}
}
