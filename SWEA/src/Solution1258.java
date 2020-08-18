import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution1258 {

	static int N, cnt = 0, map[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int mx, my;

	public static class ans implements Comparable<ans> {
		int x;
		int y;

		public ans(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(ans o) {
			// ũ�Ⱑ ���� ��� ���� ���� ������
			if(this.x * this.y == o.x * o.y)
				return Integer.compare(this.x, o.x);
			else 
				return Integer.compare(this.x * this.y, o.x * o.y);
		}

	}

	public static void dfs(int x, int y) {
		// ������ �������� ǥ��
		map[x][y] = 0;
		// �ֺ��� Ž��
		for (int d = 0; d < 4; d++) {
			int xx = x + dx[d];
			int yy = y + dy[d];
			// ������ �Ѿ�ų� �� �����̶��
			if (xx < 0 || yy < 0 || xx >= N || yy >= N ||
					map[xx][yy] == 0) {
				// ������ ����				
				mx = mx < xx ? xx : mx;
				my = my < yy ? yy : my;
				continue;
			}
			dfs(xx, yy);
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ArrayList<ans> ansLst = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// �� �����̶�� pass
					if(map[i][j] == 0) continue;
					mx = -987654321;
					my = -987654321;
					dfs(i, j);
					cnt++;
					// ������ ũ�⸦ list�� �ֱ�
					ansLst.add(new ans(mx-i, my-j));
				}
			}
			
			// �������� �࿭ �� ���� ��������
			Collections.sort(ansLst);
			System.out.print("#" + tc + " " + cnt + " ");
			for (ans a : ansLst) 
				System.out.print(a.x + " " + a.y + " ");
			
			System.out.println();
		}

	}

}
