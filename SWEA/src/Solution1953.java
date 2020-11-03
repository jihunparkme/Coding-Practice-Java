import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1953 {

	static int H, W, R, C, L, res, map[][];
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
	static int[][] nextType = { { 1, 2, 5, 6 }, { 1, 2, 4, 7 }, { 1, 3, 4, 5 }, { 1, 3, 6, 7 } };
	static class State {
		int r, c, cnt;

		public State(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken()); // 지하 터널의 크기
			W = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑의 위치
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요 시간

			map = new int[H][W];
			visited = new boolean[H][W];
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			res = 1;
			process();
			
			System.out.println("#" + tc + " " + res);
		}
	}

	private static void process() {

		State[] next = new State[4]; // 상,하,좌,우
		Queue<State> q = new LinkedList<>();
		// 초기 위치
		q.add(new State(R, C, 1));
		visited[R][C] = true;

		while (!q.isEmpty()) {

			State now = q.poll();
			// 요소 시간에 도달하면 종료
			if (now.cnt == L) return;

			int type = map[now.r][now.c];
			// 다음으로 좌표를 미리 저장
			for (int d = 0; d < 4; d++) {
				int rr = now.r + dr[d];
				int cc = now.c + dc[d];
				// 범위를 벗어나거나 이미 방문한 곳이거나 길이 없으면 -1
				if (rr < 0 || rr >= H || cc < 0 || cc >= W || visited[rr][cc] || map[rr][cc] == 0) {
					next[d] = new State(-1, -1, -1);
				} else {
					next[d] = new State(rr, cc, now.cnt + 1);
				}
			}

			State nLoc;
			// 연결된 터널을 찾고 다음 위치로 갈 수 있을 경우 queue에 추가
			// 위에 있는 터널과 연결될 경우
			if (type == 1 || type == 2 || type == 4 || type == 7) {
				nLoc = next[0];
				if (check(nLoc, 0)) q.add(nLoc);
			}
			// 아래에 있는 터널과 연결될 경우
			if (type == 1 || type == 2 || type == 5 || type == 6) {
				nLoc = next[1];
				if (check(nLoc, 1)) q.add(nLoc);
			}
			// 좌측에 있는 터널과 연결될 경우
			if (type == 1 || type == 3 || type == 6 || type == 7) {
				nLoc = next[2];
				if (check(nLoc, 2)) q.add(nLoc);
			}
			// 우측에 있는 터널과 연결될 경우
			if (type == 1 || type == 3 || type == 4 || type == 5) {
				nLoc = next[3]; 
				if (check(nLoc, 3)) q.add(nLoc);
			}
		}
	}

	private static boolean check(State next, int dir) {

		// 다음 위치로 갈 수 없다면
		if (next.r == -1)
			return false;

		int nType = map[next.r][next.c]; // 다음 위치의 구조물
		boolean isPos = false;
		for (int i = 0; i < 4; i++) {
			if (nextType[dir][i] == nType)
				isPos = true;
		}
		// 다음 위치에 갈 수 있는 구조물이 없다면
		if (!isPos)
			return false;

		visited[next.r][next.c] = true;
		res++;

		return true;
	}

}

/*
 * 상 : 1, 2, 4, 7 
 * 하 : 1, 2, 5, 6 
 * 좌 : 1, 3, 6, 7 
 * 우 : 1, 3, 4, 5
 */
