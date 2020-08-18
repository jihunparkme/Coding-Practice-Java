import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2468 {

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
		// 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수
		// 아무 지역도 물에 잠기지 않는 경우는 1 이므로 1로 초기화
		int res = 1;
		// 비는 0부터 올 수도 있음
		for (int r = 0; r <= mx; r++) {
			// 물에 잠기는 지점은 -1로
			rain(r);
			// 안전 영역의 개수를 찾아보자.
			visited = new boolean[N][N];
			cnt = 0;
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					// 잠기지 않은 영역이라면
					if (map[x][y] > 0 && !visited[x][y]) {
						// 안전 영역의 개수를 세보자
						find(x, y);
						cnt++;
					}
				}
			}
			res = Math.max(res, cnt);
		}

		System.out.println(res);
	}

	public static void rain(int h) {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				// 높이 이하인 모든 지점이 물에 잠김
				if (map[x][y] <= h) map[x][y] = -1;
			}
		}
	}

	public static void find(int x, int y) {
		visited[x][y] = true;

		for (int d = 0; d < 4; d++) {
			int xx = x + dx[d];
			int yy = y + dy[d];
			// 범위를 벗어나면 pass
			if (xx < 0 || yy < 0 || xx >= N || yy >= N) continue;
			// 이미 방문한 곳이거나 물에 잠긴 곳이면 pass
			if (visited[xx][yy] || map[xx][yy] < 0) continue;
			// 그렇지 않다면
			find(xx, yy);
		}
	}
}
