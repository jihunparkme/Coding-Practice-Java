import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2468_dfs {

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int map[][];
	static boolean visited[][];
	static int N, cnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int mx = -987654321;
		// 행과 열의 크기가 각각 N
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 최대, 최소 지점 구하기
				if (map[i][j] > mx) mx = map[i][j];
			}
		}
		// 아무 지역도 물에 잠기지 않는 경우 안전 영역이 1 이므로 1 로 초기화
		int res = 1;
		// 물에 잠기지 않는 안전한 영역의 최대 개수를 찾아보자.
		// 비는 0부터 올 수도 있음(비가 안 오는 경우)
		for (int r = 0; r <= mx; r++) {
			// 비가 올 때마다 지역들을 다시 확인해줘야하므로 계속 visited를 초기화
			visited = new boolean[N][N];
			cnt = 0;
			// 모든 지역을 확인하면서
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					// 물에 잠기지 않고, 방문하지 않은 지역이라면
					if (map[x][y] > r && !visited[x][y]) {
						// 안전 영역의 개수 count
						find(x, y, r);
						cnt++;
					}
				}
			}
			res = Math.max(res, cnt);
		}

		System.out.println(res);
	}

	public static void find(int x, int y, int rr) {
		// 방문 처리
		visited[x][y] = true;
		// 사방을 탐색
		for (int d = 0; d < 4; d++) {
			int xx = x + dx[d];
			int yy = y + dy[d];
			// 범위를 벗어나면 pass
			if (xx < 0 || yy < 0 || xx >= N || yy >= N) continue;
			// 이미 방문한 곳이거나 물에 잠긴 곳이면 pass
			if (visited[xx][yy] || map[xx][yy] <= rr) continue;
			// 그렇지 않다면 해당 지역에 방문해보기
			find(xx, yy, rr);
		}
	}
}
