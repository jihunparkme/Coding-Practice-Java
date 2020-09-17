import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2583 {

	static int M, N, K;
	static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };
	static boolean map[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new boolean[M][N];

		// 직사각형 그리기
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;

			int startx = (M - 1) - b;
			int starty = a;
			int endx = (M - 1) - d;
			int endy = c;

			for (int i = endx; i <= startx; i++) {
				for (int j = starty; j <= endy; j++) {
					map[i][j] = true;
				}
			}
		}

		ArrayList<Integer> cntList = new ArrayList<>();
		int total = 0;
		// 나누어지는 영역의 개수와 영역의 넓이를 오름차순으로 정렬하여 출력
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!map[i][j]) {
					total++;
					cntList.add(dfs(i, j));
				}
			}
		}
		
		System.out.println(total);
		cntList.sort(null);
		for (Integer i : cntList) {
			System.out.print(i + " ");
		}
	}

	private static int dfs(int x, int y) {
		
		int cnt = 1;
		map[x][y] = true;
		
		for (int d = 0; d < 4; d++) {
			int xx = x + dx[d];
			int yy = y + dy[d];
			// 범위
			if(xx < 0 || yy < 0 || xx >= M || yy >= N) continue;
			if(map[xx][yy]) continue;
			cnt += dfs(xx, yy);
		}
		
		return cnt;
	}
}
