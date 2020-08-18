import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution7699 {

	static int T, R, C, res;
	static char map[][];
	static boolean alpa[];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static void dfs(int x, int y, int cnt) {
		res = Math.max(res, cnt);
		// 모든 명물을 다 보게될 경우 어차피 더이상 확인할 필요가 없으므로 return
		if(res == 26) return;
		// 명물 보기
		alpa[map[x][y] - 'A'] = true;
		
		for (int d = 0; d < 4; d++) {
			int xx = x + dx[d];
			int yy = y + dy[d];
			// 범위를 벗어나면 pass
			if (xx < 0 || yy < 0 || xx >= R || yy >= C) continue;
			// 이미 본 명물이거나 이미 방문한 영역이라면 pass
			if (alpa[map[xx][yy] - 'A']) continue;
			// 이동
			dfs(xx, yy, cnt+1);
			alpa[map[xx][yy] - 'A'] = false;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			alpa = new boolean[26];

			for (int i = 0; i < R; i++) {
				String tmp = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = tmp.charAt(j);
				}
			}
			// 탐색 시작
			res = 0;
			// 수지는 (0,0)에서 출발
			dfs(0, 0, 1);
			
			System.out.println("#" + tc + " " + res);
		}
	
	}
}