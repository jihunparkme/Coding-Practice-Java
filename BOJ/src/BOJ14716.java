import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14716 {

	static int N, M, res, map[][];
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }, dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 1) {
					dfs(i, j);
					res++;
				}
			}
		}

		System.out.println(res);
	}

	private static void dfs(int r, int c) {
		
		for (int d = 0; d < 8; d++) {
			int rr = r + dr[d];
			int cc = c + dc[d];
			// 범위를 벗어나면 pass
			if(rr < 0 || cc < 0 || rr >= N || cc >= M) continue;
			// 글자의 일부가 아니면
			if(map[rr][cc] == 0) continue;
			
			map[rr][cc] = 0;
			dfs(rr, cc);
		}
		
	}

}
