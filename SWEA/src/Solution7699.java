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
		// ��� ���� �� ���Ե� ��� ������ ���̻� Ȯ���� �ʿ䰡 �����Ƿ� return
		if(res == 26) return;
		// �� ����
		alpa[map[x][y] - 'A'] = true;
		
		for (int d = 0; d < 4; d++) {
			int xx = x + dx[d];
			int yy = y + dy[d];
			// ������ ����� pass
			if (xx < 0 || yy < 0 || xx >= R || yy >= C) continue;
			// �̹� �� ���̰ų� �̹� �湮�� �����̶�� pass
			if (alpa[map[xx][yy] - 'A']) continue;
			// �̵�
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
			// Ž�� ����
			res = 0;
			// ������ (0,0)���� ���
			dfs(0, 0, 1);
			
			System.out.println("#" + tc + " " + res);
		}
	
	}
}